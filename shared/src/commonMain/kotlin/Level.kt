import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.colorArrayFor4Tracks
import theme.colorArrayFor8Tracks
import theme.revolvingBallsRadiusArrayFor4Tracks
import theme.revolvingBallsRadiusArrayFor8Tracks
import theme.trackDiameterFor4Tracks
import theme.trackDiameterFor8Tracks

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
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            80,
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
            60,
            listOf(10, 20, 30)
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
            90,
            listOf(10, 20, 40)
        )
    )

    levelList.add(
        Level(
            6, 3,
            listOf(0f, 360f, 0f), listOf(360f, 0f, 360f),
            listOf(1800, 2100, 2400),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            4, 4,
            100,
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
            80,
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
            80,
            listOf(10, 20, 30, 40)
        )
    )

    levelList.add(
        Level(
            9, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2400, 3000, 3600),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            90,
            listOf(10, 20, 30, 40)
        )
    )

    levelList.add(
        Level(
            10, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            100,
            listOf(10, 20, 30, 40)
        )
    )

    levelList.add(
        Level(
            11, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2700, 2100, 2400),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            4, 4,
            110,
            listOf(10, 20, 30, 40)
        )
    )

    levelList.add(
        Level(
            12, 5,
            listOf(0f, 0f, 0f, 0f, 0f), listOf(360f, 360f, 360f, 360f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            100,
            listOf(10, 20, 30, 40, 50)
        )
    )

    levelList.add(
        Level(
            13, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            100,
            listOf(10, 20, 30, 40, 50)
        )
    )

    levelList.add(
        Level(
            14, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2100, 2400, 2700, 3000),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            100,
            listOf(10, 20, 30, 40, 50)
        )
    )

    levelList.add(
        Level(
            15, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2400, 2100, 3000, 2700),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            100,
            listOf(10, 20, 30, 40, 50)
        )
    )

    levelList.add(
        Level(
            16, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2400, 2100, 3000, 2700),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            4, 4,
            120,
            listOf(10, 20, 30, 40, 50)
        )
    )

    levelList.add(
        Level(
            17, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2400, 2100, 3000, 2700),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            120,
            listOf(10, 20, 30, 40, 50)
        )
    )

    levelList.add(
        Level(
            18, 6,
            listOf(0f, 0f, 0f, 0f, 0f, 0f), listOf(360f, 360f, 360f, 360f, 360f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            120,
            listOf(10, 20, 30, 40, 50, 60)
        )
    )

    levelList.add(
        Level(
            19, 6,
            listOf(0f, 360f, 0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f, 360f, 0f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            120,
            listOf(10, 20, 30, 40, 50, 60)
        )
    )

    levelList.add(
        Level(
            20, 6,
            listOf(0f, 360f, 0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700, 3000, 3300),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            120,
            listOf(10, 20, 30, 40, 50, 60)
        )
    )

    levelList.add(
        Level(
            21, 6,
            listOf(0f, 360f, 0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700, 3000, 3300),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            140,
            listOf(10, 20, 30, 40, 50, 60)
        )
    )

    levelList.add(
        Level(
            22, 6,
            listOf(0f, 360f, 0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2400, 2100, 3300, 2700, 3000),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            140,
            listOf(10, 20, 30, 40, 50, 60)
        )
    )

    levelList.add(
        Level(
            23, 7,
            listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f), listOf(360f, 360f, 360f, 360f, 360f, 360f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600, 3900),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            150,
            listOf(10, 20, 30, 40, 50, 60, 70)
        )
    )

    levelList.add(
        Level(
            24, 7,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600, 3900),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            150,
            listOf(10, 20, 30, 40, 50, 60, 70)
        )
    )

    levelList.add(
        Level(
            25, 7,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2100, 2400, 2700, 3000, 3300, 3600),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            180,
            listOf(10, 20, 30, 40, 50, 60, 70)
        )
    )

    levelList.add(
        Level(
            26, 7,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2400, 2100, 3300, 3600, 2700, 3000),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            180,
            listOf(10, 20, 30, 40, 50, 60, 70)
        )
    )

    levelList.add(
        Level(
            27, 7,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2400, 2100, 3300, 3600, 2700, 3000),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            200,
            listOf(10, 20, 30, 40, 50, 60, 70)
        )
    )

    levelList.add(
        Level(
            28,
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
            200,
            listOf(10, 20, 30, 40, 50, 60, 70, 80)
        )
    )

    levelList.add(
        Level(
            29,
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
            200,
            listOf(10, 20, 30, 40, 50, 60, 70, 80)
        )
    )

    levelList.add(
        Level(
            30,
            8,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700, 3000, 3200, 3400, 3600),
            true,
            3.dp,
            12.dp,
            revolvingBallsRadiusArrayFor8Tracks,
            trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            200,
            listOf(10, 20, 30, 40, 50, 60, 70, 80)
        )
    )

    levelList.add(
        Level(
            31,
            8,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700, 3000, 3200, 3400, 3600),
            true,
            3.dp,
            12.dp,
            revolvingBallsRadiusArrayFor8Tracks,
            trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            220,
            listOf(10, 20, 30, 40, 50, 60, 70, 80)
        )
    )

    levelList.add(
        Level(
            31,
            8,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2400, 2100, 3400, 3600, 2700, 3000, 3200),
            true,
            3.dp,
            12.dp,
            revolvingBallsRadiusArrayFor8Tracks,
            trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            220,
            listOf(10, 20, 30, 40, 50, 60, 70, 80)
        )
    )

    levelList.add(
        Level(
            31,
            8,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2400, 2100, 3400, 3600, 2700, 3000, 3200),
            true,
            3.dp,
            12.dp,
            revolvingBallsRadiusArrayFor8Tracks,
            trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            4, 4,
            250,
            listOf(10, 20, 30, 40, 50, 60, 70, 80)
        )
    )

    return levelList
}
