import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    gameState: MutableState<State>,
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
                    // You Win, as roller has reached destination without collision
                    stopAllAnimations(angles, rollerStopped, gameState, State.Win, 0)
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
    gameState: MutableState<State>,
    angles: List<Animatable<Float, AnimationVector1D>>
){
    var buttonEnabled = remember { mutableStateOf(false) }
    var firstRoundCompleted = remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)) {
        Button(
            enabled = buttonEnabled.value,
            onClick = {
                // disable button after click
                buttonEnabled.value = false
                // To calculate if roller touches the revolving balls, if true, stop roller and balls
                fireTheRoller(level.trackCount, angles, rollerStarted, rollerStopped, gameState)
            })
        {
            Text(text = "Start")
        }
    }

    // Condition to execute handler only once
    if(!firstRoundCompleted.value) {
        // Enable start button after one revolution of ball (ball which has longest revolution time)
        GlobalScope.launch() {
            delay((level.ballAnimationDuration.maxOrNull() ?: 0).toLong())
            buttonEnabled.value = true
            firstRoundCompleted.value = true
        }
    }
}
