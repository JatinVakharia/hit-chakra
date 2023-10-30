package ui

import SCREEN_HEIGHT_DP
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
import fixedExtraHeightDP
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import theme.darkYellow

@Composable
fun MissedAnimation() {

    val textScale = remember {
        Animatable(1f)
    }

    val coroutineScope = rememberCoroutineScope()

    var show by remember {
        mutableStateOf(true)
    }

    AnimatedVisibility(
        visible = show,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SCREEN_HEIGHT_DP.dp / 2 + fixedExtraHeightDP)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Box(modifier = Modifier
                .padding(top = 150.dp)) {
                Text(
                    text = "You Missed",
                    textAlign = TextAlign.Center,
                    color = darkYellow,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(150.dp)
                        .scale(textScale.value)
                )
            }
        }
    }

    coroutineScope.launch {
        coroutineScope.launch {
            textScale.animateTo(0.6f, tween(500, easing = LinearEasing))
        }

        coroutineScope.launch {
            delay(300)
            coroutineScope.launch {
                textScale.animateTo(1f)
            }
        }

        coroutineScope.launch {
            delay(1500)
            show = false
        }
    }

}