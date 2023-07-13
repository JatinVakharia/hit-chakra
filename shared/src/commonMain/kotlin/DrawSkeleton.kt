import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun drawBallsWithPoints(level: Level) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp)
    ) {
        for (index in 0 until level.ballPoints.size) {
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(level.ballSizeInDp)
                    .background(
                        color = level.colorArray[index],
                        shape = CircleShape
                    )
            ) {
                Text(
                    text = "${level.ballPoints[index]}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun drawHeartsAndScore(level: Level) {
    val infiniteTransition = rememberInfiniteTransition()
    val pulsate by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(tween(400), RepeatMode.Reverse)
    )

    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 20.dp, bottom = 0.dp, end = 0.dp, top = 35.dp)
    ) {
        var tempLifeRemaining = level.livesRemaining
        for (index in 0 until level.livesAllotted) {
            tempLifeRemaining--
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                painter = painterResource("heart.xml"),
                contentDescription = null,
                tint = if (tempLifeRemaining >= 0) Color.Red else Color.Gray,
                modifier = Modifier
                    .scale(if (tempLifeRemaining >= 0) pulsate else 1f)
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 0.dp, bottom = 0.dp, end = 20.dp, top = 35.dp)
    ) {
        Text(
            text = "Score : ",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Text(
            text = "${level.pointsEarned}",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
        Text(
            text = "/${level.pointsToWin}",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun drawLevelLabel(level: Level) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 20.dp, bottom = 20.dp)
    ) {
        Text(
            text = "Level : ${level.level}",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun drawTracks(index: Int, screenCenterX: Int, screenCenterY: Int, level: Level) {
    val trackDiameterInPx = with(LocalDensity.current) { level.trackDiameter[index].dp.toPx() }
    Surface(
        modifier = Modifier
            .size(level.trackDiameter[index].dp)
            .graphicsLayer {
                translationX = screenCenterX.toFloat() - (trackDiameterInPx / 2)
                translationY = screenCenterY.toFloat() - (trackDiameterInPx / 2)
            },
        color = Color.Transparent,
        shape = CircleShape,
        border = BorderStroke(width = level.trackWidthInDp, color = Color.Gray)
    ) {}
}

@Composable
fun drawRevolvingBalls(
    index: Int,
    angle: Animatable<Float, AnimationVector1D>,
    screenCenterX: Int,
    screenCenterY: Int,
    ballSizeInPx: Float,
    level: Level
) {
    val radius = with(LocalDensity.current) { level.revolvingBallsRadiusArray[index].dp.toPx() }
    Box(modifier = Modifier
        .size(level.ballSizeInDp)
        .graphicsLayer {
            translationX = screenCenterX + getXCoOrdFromAngle(
                angle.value,
                radius
            ).toFloat() - (ballSizeInPx / 2)
            translationY = screenCenterY + getYCoOrdFromAngle(
                angle.value,
                radius
            ).toFloat() - (ballSizeInPx / 2)
            generateIntersectTimestampList(
                index,
                angle.value,
                level
            )
        }
        .background(
            color = level.colorArray[index],
            shape = CircleShape
        )
    )
}

@Composable
fun drawSourceRing(
    x: Int,
    y: Int,
    ringSizeInPx: Float,
    srcDestRingSize: Dp,
    srcDestRingStroke: Dp
) {
    Surface(
        modifier = Modifier
            .size(srcDestRingSize)
            .graphicsLayer {
                translationX = x.toFloat() - (ringSizeInPx / 2)
                translationY = y.toFloat() - (ringSizeInPx / 2)
            },
        color = Color.Transparent,
        shape = CircleShape,
        border = BorderStroke(width = srcDestRingStroke, color = Color.Green)
    ) {}
}

@Composable
fun drawDestinationRing(
    x: Int,
    y: Int,
    ringSizeInPx: Float,
    srcDestRingSize: Dp,
    srcDestRingStroke: Dp
) {
    Surface(
        modifier = Modifier
            .size(srcDestRingSize)
            .graphicsLayer {
                translationX = x.toFloat() - (ringSizeInPx / 2)
                translationY = y.toFloat() - (ringSizeInPx / 2)
            },
        color = Color.Transparent,
        shape = CircleShape,
        border = BorderStroke(width = srcDestRingStroke, color = Color.Red)
    ) {}
}

@Composable
fun drawRoller(
    rollerSourceX: Float,
    rollerSourceY: Float,
    destX: Float,
    destY: Float,
    rollerStarted: MutableState<Boolean>,
    rollerStopped: MutableState<Boolean>,
    levelState: MutableState<Int>,
    angles: List<Animatable<Float, AnimationVector1D>>
) {

    var previousX = 0f
    var previousY = 0f

    val xValue = animateFloatAsState(
        targetValue = if (rollerStarted.value) destX else rollerSourceX,
        animationSpec = tween(rollerAnimTime, 0, LinearEasing)
    )

    val yValue = animateFloatAsState(
        targetValue = if (rollerStarted.value) destY else rollerSourceY,
        animationSpec = tween(rollerAnimTime, 0, LinearEasing)
    )

    Box(
        modifier = Modifier
            .size(rollerSize)
            .graphicsLayer {
                if (!rollerStopped.value) {
                    previousX = xValue.value
                    previousY = yValue.value
                }
                translationX = previousX
                translationY = previousY
                if (previousX == destX && previousY == destY) {
                    // You missed the chance, as roller has reached destination without collision
                    stopAllAnimations(angles, rollerStopped, levelState, 0, 0)
                }
            }
            .background(
                color = Color.White,
                shape = CircleShape
            )
    )
}

@Composable
fun drawStartButton(
    level: Level,
    rollerStarted: MutableState<Boolean>,
    rollerStopped: MutableState<Boolean>,
    levelState: MutableState<Int>,
    angles: List<Animatable<Float, AnimationVector1D>>
) {
    var buttonEnabled = remember { mutableStateOf(false) }
    var firstRoundCompleted = remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
    ) {
        Button(
            enabled = buttonEnabled.value,
            shape = CircleShape,
            onClick = {
                // disable button after click
                buttonEnabled.value = false
                // To calculate if roller touches the revolving balls, if true, stop roller and balls
                fireTheRoller(level, angles, rollerStarted, rollerStopped, levelState)
            })
        {
            Text(text = "Start")
        }
    }

    // Condition to execute handler only once
    if (!firstRoundCompleted.value) {
        // Enable start button after one revolution of ball (ball which has longest revolution time)
        GlobalScope.launch() {
            delay((level.ballAnimationDuration.maxOrNull() ?: 0).toLong())
            buttonEnabled.value = true
            firstRoundCompleted.value = true
        }
    }
}
