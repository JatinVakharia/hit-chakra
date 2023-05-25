package ui

import androidx.compose.foundation.shape.GenericShape
import kotlin.math.PI
import kotlin.math.tan
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

class CustomShape {
    object Shapes{
        val Triangle = GenericShape { size, _ ->

            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
        }
        val Parallelogram = GenericShape { size, _ ->

            val radian = (90 - 60) * PI / 180
            val xOnOpposite = (size.height * tan(radian)).toFloat()
            moveTo(0f, size.height)
            lineTo(x = xOnOpposite, y = 0f)
            lineTo(x = size.width, y = 0f)
            lineTo(x = size.width - xOnOpposite, y = size.height)
            lineTo(x = xOnOpposite, y = size.height)
        }
    }
}

fun Color.Companion.random() : Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}

