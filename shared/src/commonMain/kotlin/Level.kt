import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.colorArrayFor4Tracks
import theme.revolvingBallsRadiusArrayFor4Tracks
import theme.trackDiameterFor4Tracks

data class Level(
    val level: Int,
    val trackCount: Int,
    val ballInitialAngle: List<Float>,
    val ballTargetAngle: List<Float>,
    val ballAnimationDuration: List<Int>,
    val crossHalf: Boolean,
    val trackWidthInDp: Dp,
    val ballSizeInDp: Dp,
    val revolvingBallsRadiusArray: List<Int>,
    val trackDiameter: List<Int>,
    val colorArray: List<Color>,
    val livesAllotted: Int,
    var livesRemaining: Int,
    val pointsToWin : Int,
    val ballPoints : List<Int>,
    var pointsEarned : Int = 0
)

fun getLevelObjects(): List<Level> {
    val levelList = ArrayList<Level>()

    levelList.add(
        Level(
            1, 2,
            listOf(0f, 0f), listOf(360f, 360f),
            listOf(2400, 3000),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            50,
            listOf(20, 40)
        )
    )

    levelList.add(
        Level(
            2, 2,
            listOf(0f, 360f), listOf(360f, 0f),
            listOf(1800, 2400),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            60,
            listOf(20, 40)
        )
    )

    levelList.add(
        Level(
            3, 2,
            listOf(0f, 360f), listOf(360f, 0f),
            listOf(1800, 2400),
            false,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            70,
            listOf(20, 40)
        )
    )

    levelList.add(
        Level(
            4, 3,
            listOf(0f, 0f, 0f), listOf(360f, 360f, 360f),
            listOf(1800, 2400, 3000),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            50,
            listOf(10, 20, 40)
        )
    )

    levelList.add(
        Level(
            5, 3,
            listOf(0f, 360f, 0f), listOf(360f, 0f, 360f),
            listOf(1800, 2400, 3000),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            50,
            listOf(10, 20, 30)
        )
    )

    levelList.add(
        Level(
            6, 3,
            listOf(0f, 360f, 0f), listOf(360f, 0f, 360f),
            listOf(1800, 2400, 3000),
            false,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            50,
            listOf(10, 20, 40)
        )
    )

    levelList.add(
        Level(
            7, 4,
            listOf(0f, 0f, 0f, 0f), listOf(360f, 360f, 360f, 360f),
            listOf(1800, 2400, 3000, 3600),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            50,
            listOf(10, 20, 30, 40)
        )
    )

    levelList.add(
        Level(
            8, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2400, 3000, 3600),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            50,
            listOf(10, 20, 30, 40)
        )
    )

    levelList.add(
        Level(
            9, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2400, 3000, 3600),
            false,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            50,
            listOf(10, 20, 30, 40)
        )
    )

    /*levelList.add(
        Level(
            10, 5,
            listOf(0f, 0f, 0f, 0f, 0f), listOf(360f, 360f, 360f, 360f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            11, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            12, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2100, 2400, 2700, 3000),
            false,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            13, 6,
            listOf(0f, 0f, 0f, 0f, 0f, 0f), listOf(360f, 360f, 360f, 360f, 360f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            14, 6,
            listOf(0f, 360f, 0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f, 360f, 0f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            15, 6,
            listOf(0f, 360f, 0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700, 3000, 3300),
            false,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            16, 7,
            listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f), listOf(360f, 360f, 360f, 360f, 360f, 360f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600, 3900),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            17, 7,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600, 3900),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            18, 7,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2100, 2400, 2700, 3000, 3300, 3600),
            false,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            19,
            8,
            listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
            listOf(360f, 360f, 360f, 360f, 360f, 360f, 360f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600, 3900, 4200),
            true,
            3.dp,
            12.dp,
            revolvingBallsRadiusArrayFor8Tracks,
            trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            20,
            8,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f, 0f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600, 3900, 4200),
            true,
            3.dp,
            12.dp,
            revolvingBallsRadiusArrayFor8Tracks,
            trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )

    levelList.add(
        Level(
            21,
            8,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700, 3000, 3200, 3400, 3600),
            false,
            3.dp,
            12.dp,
            revolvingBallsRadiusArrayFor8Tracks,
            trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            50
        )
    )*/

    return levelList
}
