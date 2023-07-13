package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import theme.darkYellow

@Composable
fun PointsAnimation() {

    val ringScale = remember {
        Animatable(0f)
    }
    val ringOpacity = remember {
        Animatable(1f)
    }
    val textScale = remember {
        Animatable(1f)
    }

    val textRotation = remember {
        Animatable(0f)
    }

    val orbOffset = remember {
        Animatable(0f)
    }

    val orbScale = remember {
        Animatable(0f)
    }

    val color = remember {
        mutableStateOf(Color.White)
    }

    val coroutineScope = rememberCoroutineScope()

    var show by remember {
        mutableStateOf(true)
    }

    AnimatedVisibility(
        visible = show,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            val offsetEven = with(LocalDensity.current) {
                orbOffset.value.toDp()
            }
            val offsetOdd = with(LocalDensity.current) {
                orbOffset.value.toDp()
                    .div(1.2.dp)
            }

            Box {
                for (item in 0 until 8) {
                    Canvas(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .rotate(item.times(45f))
                            .offset(
                                x = 0.dp,
                                y = (if (item % 2 == 0) offsetEven else offsetOdd.dp)
                            )
                            .scale(orbScale.value),
                        onDraw = {
                            drawCircle(
                                color = darkYellow,
//                                    color = if (item % 2 == 0) Color.Blue else Color.Red,
                                alpha = if (item % 2 == 0) 1f else 0.5f,
                                radius = if (item % 2 == 0) 150f else 50f
                            )
                        })
                }

                Canvas(modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.Center)
                    .scale(ringScale.value), onDraw = {
                    drawCircle(
                        color = darkYellow.copy(alpha = ringOpacity.value),
                        style = Stroke(width = 8f)
                    )
                })

                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .wrapContentSize()
                        .size(150.dp)
                        .background(
                            color = darkYellow,
                            shape = CircleShape
                        )
                        .align(Alignment.Center),
                ) {
                    Text(
                        text = "You Missed",
                        textAlign = TextAlign.Center,
                        color = color.value,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(100.dp)
                            .scale(textScale.value)
                            .rotate(textRotation.value)
                    )
                }

            }
        }
    }

    coroutineScope.launch {
//        delay(500)
        coroutineScope.launch {
            orbOffset.animateTo(0f)
            ringOpacity.animateTo(1f)
            ringScale.animateTo(0f)
            textScale.animateTo(0.6f, tween(500, easing = LinearEasing))
            textRotation.animateTo(-30f, tween(500, easing = LinearEasing))
        }

        coroutineScope.launch {
            delay(300)
            coroutineScope.launch {
                orbOffset.animateTo(orbOffset.value.minus(250f), tween(1000))
            }
            color.value = Color.Black
            coroutineScope.launch {
                orbScale.animateTo(1f)
                ringScale.animateTo(1f)
                textScale.animateTo(1f)
                ringOpacity.animateTo(0f)
                textRotation.animateTo(0f)
            }
        }

        coroutineScope.launch {
            delay(300)
            orbScale.animateTo(0f, tween(1200))
        }

        coroutineScope.launch {
            delay(1500)
            show = false
        }
    }

}