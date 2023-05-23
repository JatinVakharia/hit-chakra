import androidx.compose.animation.core.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import mu.KotlinLogging
import kotlin.math.PI

private val logger = KotlinLogging.logger {}
val srcDestRingStroke = 5.dp
val srcDestRingSize = 35.dp
val rollerSize = 25.dp
var rollerAnimTime = 2000

/** xIntersectList stores all x coordinates of intersection between roller and every track */
lateinit var xIntersectList: ArrayList<Float>

/** yIntersectList stores all y coordinates of intersection between roller and every track */
lateinit var yIntersectList: ArrayList<Float>

/** timeRequiredList stores time in millisecond, which is the time required by roller to touch every track */
lateinit var timeRequiredList: ArrayList<Long>

/** intersectTimestampList stores time in timestamp, which is the exact time-range (min, max) in future,
 * when roller will touch every track */
lateinit var intersect180TimestampList: ArrayList<LongArray>
lateinit var intersect360TimestampList: ArrayList<LongArray>

lateinit var angleCoveredOfTrackByRoller: ArrayList<Float>

var rollerTimeToCrossTrack: Long = 0

fun initiateData(trackCount: Int) {
    // a condition to protect from reinitialization of variable
    if (!::xIntersectList.isInitialized) {
        xIntersectList = ArrayList(trackCount)
        yIntersectList = ArrayList(trackCount)
        timeRequiredList = ArrayList(trackCount)
        intersect180TimestampList = ArrayList(trackCount)
        intersect360TimestampList = ArrayList(trackCount)
        angleCoveredOfTrackByRoller = ArrayList(trackCount)
    }
}

fun clearData() {
    xIntersectList.clear()
    yIntersectList.clear()
    timeRequiredList.clear()
    intersect180TimestampList.clear()
    intersect360TimestampList.clear()
    angleCoveredOfTrackByRoller.clear()
}

@Composable
fun BallsRevolving(
    level: Level,
    gameState: MutableState<State>,
    screenWidthDp: Int,
    screenHeightDp: Int
) {
    logger.debug { "Level : ${level.level}" }
    // initiate all data
    initiateData(level.trackCount)
    logger.debug { "screenWidthDp : ${screenWidthDp}" }
    logger.debug { "screenHeightDp : ${screenHeightDp}" }
    // mobile device density, used to convert dp to pixel
    val density = LocalDensity.current

    val screenWidthPx = with(density) { screenWidthDp.dp.roundToPx() }
    val screenHeightPx = with(density) { screenHeightDp.dp.roundToPx() }
    logger.debug { "screenWidthPx : ${screenWidthPx}" }
    logger.debug { "screenHeightPx : ${screenHeightPx}" }
    // center co-ordinates of screen
    val screenCenterX = screenWidthPx / 2
    val screenCenterY = screenHeightPx / 2

    // Source ring position
    val sourceX = screenCenterX
    val sourceY = 210
    // Destination ring position
    val destX = screenCenterX
    val destY = if (level.crossHalf) screenCenterY else (screenCenterY + (screenCenterY - 300))

    rollerAnimTime = if (level.crossHalf) 2000 else 3500

    val ringSizeInPx = with(density) { srcDestRingSize.toPx() }
    val ballSizeInPx = with(density) { level.ballSizeInDp.toPx() }
    val rollerSizeInPx = with(density) { rollerSize.toPx() }
    val trackWidthInPx = with(density) { level.trackWidthInDp.toPx() }

    // Calculating roller source and destination
    val rollerSourceX = (sourceX - (ringSizeInPx / 2)) + with(density) { srcDestRingStroke.toPx() }
    val rollerSourceY = (sourceY - (ringSizeInPx / 2)) + with(density) { srcDestRingStroke.toPx() }
    val rollerDestX =
        (destX - (ringSizeInPx / 2)) + with(density) { srcDestRingStroke.toPx() }
    val rollerDestY =
        (destY - (ringSizeInPx / 2)) + with(density) { srcDestRingStroke.toPx() }

    // Roller animation variables
    val rollerStarted = remember { mutableStateOf(false) }
    val rollerStopped = remember { mutableStateOf(false) }

    // Calculate the angle covered by roller on each track while rolling
    if (angleCoveredOfTrackByRoller.isEmpty())
        calculateAngleCoveredForEachTrackByRoller(
            rollerSizeInPx,
            level.trackDiameter,
            level.trackCount
        )

    // Balls animation values
    val angles = getAnimationValue(level)
    angles.forEachIndexed { index, angle ->
        LaunchedEffect(key1 = angle) {
            angle.animateTo(
                targetValue = level.ballTargetAngle[index],
                animationSpec = infiniteRepeatable(
                    animation = tween(level.ballAnimationDuration[index], 0, LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    // draw game
    Box(modifier = Modifier.fillMaxSize())
    {
        angles.forEachIndexed { index, angle ->
            // Tracks
            drawTracks(index = index, screenCenterX, screenCenterY, level)
            // Revolving balls
            drawRevolvingBalls(index, angle, screenCenterX, screenCenterY, ballSizeInPx, level)
        }

        // draw balls with points
        drawBallsWithPoints(level)

        // draw lives
        drawHearts(level)

        // Draw destination ring to desired co-ordinates
        drawSourceRing(sourceX, sourceY, ringSizeInPx, srcDestRingSize, srcDestRingStroke)

        // Draw destination ring to desired co-ordinates
        drawDestinationRing(destX, destY, ringSizeInPx, srcDestRingSize, srcDestRingStroke)

        // Draw roller
        drawRoller(
            rollerSourceX,
            rollerSourceY,
            rollerDestX,
            rollerDestY,
            rollerStarted,
            rollerStopped,
            gameState,
            angles
        )

        drawStartButton(
            level = level,
            rollerStarted,
            rollerStopped,
            gameState,
            angles
        )
    }

    // Get co-ordinates, where roller path and track intersect for angle 180
    calculateIntersectionPointsOfRollerAndTracks(
        level.trackCount,
        180f,
        level.revolvingBallsRadiusArray,
        screenCenterX,
        screenCenterY
    )
    // calculate intersection points for the angle of 360 / 0
    if (!level.crossHalf)
        calculateIntersectionPointsOfRollerAndTracks(
            level.trackCount,
            0f,
            level.revolvingBallsRadiusArray,
            screenCenterX,
            screenCenterY
        )

    // calculate velocity of roller
    val rollerVelocity =
        getVelocity(rollerSourceX, rollerSourceY, rollerDestX, rollerDestY, rollerAnimTime)

    // Time taken by roller to cross track, it will be the constant time for each track
    rollerTimeToCrossTrack = ((trackWidthInPx + rollerSizeInPx) / rollerVelocity).toLong()

    // calculate time taken by roller to touch each track
    calculateTimeRequiredByRollerToTouchEachTrack(
        rollerSourceX,
        rollerSourceY,
        rollerVelocity,
        ballSizeInPx
    )
}

/**
 * Angle covered by roller for every track is different, which varies with diameter of track.
 * Which means calculate the track area covered by roller while rolling in angle (not in length or pixel)
 * */
@Composable
fun calculateAngleCoveredForEachTrackByRoller(
    rollerSizeInPx: Float,
    trackDiameter: List<Int>,
    trackCount: Int
) {
    var count = 0
    while (count != trackCount) {
        val trackDiameterInPx = with(LocalDensity.current) { trackDiameter[count].dp.toPx() }
        // Using formula arcMeasure  = (arcLength / radius) (180 / pi)
        // Here angle 180 is kept constant, as our target is at 180 or 360.
        // For 360, we just reverse the existing list
        val angleMargin = (rollerSizeInPx / (trackDiameterInPx / 2)) * (180 / PI.toFloat())
        logger.debug { "Count = $count :: angleMargin = $angleMargin" }
        angleCoveredOfTrackByRoller.add(angleMargin)
        count++
    }
}

fun fireTheRoller(
    trackCount: Int,
    angles: List<Animatable<Float, AnimationVector1D>>,
    rollerStarted: MutableState<Boolean>,
    rollerStopped: MutableState<Boolean>,
    gameState: MutableState<State>
) {
    // To start the roller from source to dest
    rollerStarted.value = true
    // When Roller is triggered calculate the live timestamp when it will hit resp tracks
    // Then finally verify if ball is in or around roller path at that timestamp
//    val currentTime = System.currentTimeMillis()
    val currentTime = Clock.System.now().toEpochMilliseconds()
    var isCollide = false
    // traverse reverse because last track would collide first to roller (For first half of the tracks)
    for (index in intersect180TimestampList.lastIndex downTo 0) {
        val timeWhenRollerTouchTrack = currentTime + timeRequiredList[index]
        val timeWhenRollerLeavesTrack = timeWhenRollerTouchTrack + rollerTimeToCrossTrack
        val intersectTime = intersect180TimestampList[index]

        logger.debug { "Count : $index" }
        logger.debug { "rollerTimeToCrossTrack : $rollerTimeToCrossTrack" }
        logger.debug { "timeRequiredList[index] : " + timeRequiredList[index] }
        logger.debug { "timeWhenRollerTouchTrack : $timeWhenRollerTouchTrack" }
        logger.debug { "timeWhenRollerLeavesTrac : $timeWhenRollerLeavesTrack" }
        logger.debug { "intersectTime Range : " + intersectTime[0] + " - " + intersectTime[1] }
        logger.debug { "Margin : " + (intersectTime[0] - intersectTime[1]) }

        // Find the overlapping time range of roller and ball over that particular track
        // If there is overlap, stop all the balls and roller on the first element of overlap
        val rollerRange = timeWhenRollerTouchTrack..timeWhenRollerLeavesTrack
        val ballRange = intersectTime[0]..intersectTime[1]
        val overlapList = rollerRange.intersect(ballRange)
        if (overlapList.isNotEmpty()) {
            isCollide = true
            var time: Long = overlapList.first() - currentTime
            logger.debug { "Boom Boom : count : $index" }
            stopAllAnimations(angles, rollerStopped, gameState, State.Loss, time)
            break
        }
    }

    // traverse forward because first track would collide first to roller (For second half of the tracks)
    if (!isCollide)
        for (index in 0.rangeTo(intersect360TimestampList.lastIndex)) {
            val timeWhenRollerTouchTrack = currentTime + timeRequiredList[trackCount + index]
            val timeWhenRollerLeavesTrack = timeWhenRollerTouchTrack + rollerTimeToCrossTrack
            val intersectTime = intersect360TimestampList[index]

            logger.debug { "360 Count : $index" }
            logger.debug { "360 timeWhenRollerTouchTrack : $timeWhenRollerTouchTrack" }
            logger.debug { "360 timeWhenRollerLeavesTrac : $timeWhenRollerLeavesTrack" }
            logger.debug { "360 intersectTime Range : " + intersectTime[0] + " - " + intersectTime[1] }
            logger.debug { "360 Margin : " + (intersectTime[0] - intersectTime[1]) }

            // Find the overlapping time range of roller and ball over that particular track
            // If there is overlap, stop all the balls and roller on the first element of overlap
            val rollerRange = timeWhenRollerTouchTrack..timeWhenRollerLeavesTrack
            val ballRange = intersectTime[0]..intersectTime[1]
            val overlapList = rollerRange.intersect(ballRange)
            if (overlapList.isNotEmpty()) {
                var time: Long = overlapList.first() - currentTime
                logger.debug { "360 Boom Boom : count : $index" }
                stopAllAnimations(angles, rollerStopped, gameState, State.Loss, time)
                break
            }
        }
}

/** Stop all balls and roller as there is a collision*/
fun stopAllAnimations(
    angles: List<Animatable<Float, AnimationVector1D>>,
    rollerStopped: MutableState<Boolean>,
    gameState: MutableState<State>,
    state: State,
    time: Long
) {
    GlobalScope.launch() {
        delay(time)
        for (angle in angles)
            angle.stop()
        rollerStopped.value = true
        gameState.value = state
    }
}

/**
 * It generates list of timestamps, when resp ball will reach desired angle in next cycle
 * */

// Todo Jatin : This calculations seems to be wrong, sometimes timestamp values are wrong
//  ..... need to consider ball speed??
fun generateIntersectTimestampList(index: Int, angle: Float, level: Level) {
    // Identify time, when ball will reach intersect points of track
    // If intersect points are updated then maintain a list of timestamp
    // (where timestamp is the time, when ball will reach the intersect point in next cycle)
    val angleMargin = angleCoveredOfTrackByRoller[index] / 2
    if (xIntersectList.isNotEmpty() && angle in (180f - angleMargin)..(180f + angleMargin)) {
//        val intersectTime = System.currentTimeMillis() + level.ballAnimationDuration[index]
        val intersectTime =
            Clock.System.now().toEpochMilliseconds() + level.ballAnimationDuration[index]
        if (intersect180TimestampList.size > index) {
            val prevList = intersect180TimestampList[index]
            if (intersectTime - prevList[0] > 1000) {
                // minimum timestamp
                prevList[0] = intersectTime
            } else {
//            } else if (intersectTime > prevList[1]) {
                // maximum timestamp
                prevList[1] = intersectTime
            }
            intersect180TimestampList[index] = prevList
        } else {
            intersect180TimestampList.add(longArrayOf(intersectTime, 0L))
        }
    }

    if (!level.crossHalf && xIntersectList.isNotEmpty() &&
        (angle in (360f - angleMargin)..(360f) || angle in (0f)..(0f + angleMargin))
    ) {
//        val intersectTime = System.currentTimeMillis() + level.ballAnimationDuration[index]
        val intersectTime =
            Clock.System.now().toEpochMilliseconds() + level.ballAnimationDuration[index]
        if (intersect360TimestampList.size > index) {
            val prevList = intersect360TimestampList[index]
            if (intersectTime - prevList[0] > 1000) {
                // minimum timestamp
                prevList[0] = intersectTime
            } else {
//            } else if (intersectTime > prevList[1]) {
                // maximum timestamp
                prevList[1] = intersectTime
            }
            intersect360TimestampList[index] = prevList
        } else {
            intersect360TimestampList.add(longArrayOf(intersectTime, 0L))
        }
    }
}

/**
 * It calculates the time required by roller to touch each track/ball (when ball is at a particular angle 180/360)
 * */
fun calculateTimeRequiredByRollerToTouchEachTrack(
    rollerSourceX: Float,
    rollerSourceY: Float,
    rollerVelocity: Double,
    ballSizeInPx: Float
) {
    xIntersectList.forEachIndexed { index, xPoint ->
        // gets the distance between roller start point
        // and point of intersection with ball (when ball is at a particular angle)
        val distance = getDistance(
            rollerSourceX, xPoint,
            rollerSourceY, (yIntersectList[index] - ballSizeInPx)
        )
        val time = distance / rollerVelocity
        timeRequiredList.add(time.toLong())
    }
}

/**
 * It calculates the intersection points of roller and tracks (when ball is at a particular angle)
 * */
@Composable
fun calculateIntersectionPointsOfRollerAndTracks(
    trackCount: Int,
    angle: Float,
    revolvingBallsRadiusArray: List<Int>,
    screenCenterX: Int,
    screenCenterY: Int
) {
    var count = 0
    while (count != trackCount) {
        val radius = with(LocalDensity.current) { revolvingBallsRadiusArray[count].dp.toPx() }
        xIntersectList.add(
            screenCenterX + getXCoOrdFromAngle(
                angle,
                radius
            ).toFloat()
        )
        yIntersectList.add(
            screenCenterY + getYCoOrdFromAngle(
                angle,
                radius
            ).toFloat()
        )
        count++
    }
}

@Composable
fun getAnimationValue(level: Level): List<Animatable<Float, AnimationVector1D>> {
    var count = 0
    val list = ArrayList<Animatable<Float, AnimationVector1D>>()
    while (count != level.trackCount) {
        list.add(remember { Animatable(initialValue = level.ballInitialAngle[count]) })
        count++
    }
    return list
}