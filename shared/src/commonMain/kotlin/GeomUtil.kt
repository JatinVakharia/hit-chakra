import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

enum class State { Playing, Loss, Win, NextAttempt }
enum class Behaviour { None, Retry, NextLevel, SameLevelNextAttempt, AddOneLife }

val fixedExtraHeightDP = 70.dp

/**
 * Returns the distance between two points of screen.
 * Distance is calculated using pythagorean theorem
 */
fun getDistance(
    rollerSourceX: Float, rollerDestX: Float, rollerSourceY: Float, rollerDestY: Float
): Double {
    val xDiff = abs(rollerDestX - rollerSourceX).toDouble()
    val yDiff = abs(rollerDestY - rollerSourceY).toDouble()

    // Uses Pythagorean theorem to get distance between 2 points
    return hypot(xDiff, yDiff)
}


/**
 * Returns the velocity of an object.
 * As we know distance and time taken, here velocity is calculated as
 * velocity = distance / time
 */
fun getVelocity(
    rollerSourceX: Float,
    rollerSourceY: Float,
    rollerDestX: Float,
    rollerDestY: Float,
    rollerAnimTime: Int
): Double {
    val distance = getDistance(rollerSourceX, rollerDestX, rollerSourceY, rollerDestY)

    return distance / rollerAnimTime
}

/**
 * Returns the generic x-co-ordinate of corresponding angle and radius of circle,
 * Note : it considers (0,0) as center of circle and 0, 360 angle is considered as bottom of circle
 */
fun getXCoOrdFromAngle(angle: Float, radius: Float): Double {
    return (radius * sin(PI * 2 * angle / 360))
}

/**
 * Returns the generic y-co-ordinate of corresponding angle and radius of circle,
 * Note : it considers (0,0) as center of circle and 0, 360 angle is considered as bottom of circle
 */
fun getYCoOrdFromAngle(angle: Float, radius: Float): Double {
    return (radius * cos(PI * 2 * angle / 360))
}