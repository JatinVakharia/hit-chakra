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
            60,
            listOf(30, 20)
        )
    )

    levelList.add(
        Level(
            4, 2,
            listOf(0f, 360f), listOf(360f, 0f),
            listOf(1800, 2400),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            80,
            listOf(40, 20)
        )
    )

    levelList.add(
        Level(
            5, 2,
            listOf(0f, 360f), listOf(360f, 0f),
            listOf(1800, 2100),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            80,
            listOf(30, 40)
        )
    )

    levelList.add(
        Level(
            6, 3,
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
            7, 3,
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
            8, 3,
            listOf(0f, 360f, 0f), listOf(360f, 0f, 360f),
            listOf(1800, 2400, 3000),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            90,
            listOf(10, 40, 20)
        )
    )

    levelList.add(
        Level(
            9, 3,
            listOf(0f, 360f, 0f), listOf(360f, 0f, 360f),
            listOf(1800, 2100, 2400),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            4, 4,
            100,
            listOf(40, 20, 30)
        )
    )

    levelList.add(
        Level(
            10, 3,
            listOf(0f, 360f, 0f), listOf(360f, 0f, 360f),
            listOf(1800, 2100, 2400),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            4, 4,
            100,
            listOf(10, 40, 30)
        )
    )

    levelList.add(
        Level(
            11, 4,
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
            12, 4,
            listOf(0f, 0f, 0f, 0f), listOf(360f, 360f, 360f, 360f),
            listOf(1800, 2400, 3000, 3600),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            80,
            listOf(10, 40, 30, 20)
        )
    )

    levelList.add(
        Level(
            13, 4,
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
            14, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2400, 3000, 3600),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            80,
            listOf(40, 20, 30, 10)
        )
    )

    levelList.add(
        Level(
            15, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2400, 3000, 3600),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            90,
            listOf(10, 20, 40, 30)
        )
    )

    levelList.add(
        Level(
            16, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2400, 2700, 3300),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            90,
            listOf(10, 40, 20, 30)
        )
    )

    levelList.add(
        Level(
            17, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            100,
            listOf(10, 30, 40, 20)
        )
    )

    levelList.add(
        Level(
            18, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            100,
            listOf(40, 20, 30, 10)
        )
    )

    levelList.add(
        Level(
            19, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2700, 2100, 2400),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            4, 4,
            110,
            listOf(20, 30, 40, 10)
        )
    )

    levelList.add(
        Level(
            20, 4,
            listOf(0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f),
            listOf(1800, 2700, 2100, 2400),
            true,
            10.dp, 20.dp,
            revolvingBallsRadiusArrayFor4Tracks, trackDiameterFor4Tracks,
            colorArrayFor4Tracks,
            3, 3,
            110,
            listOf(10, 40, 30, 20)
        )
    )

    levelList.add(
        Level(
            21, 5,
            listOf(0f, 0f, 0f, 0f, 0f), listOf(360f, 360f, 360f, 360f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            100,
            listOf(10, 50, 30, 40, 20)
        )
    )

    levelList.add(
        Level(
            22, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            100,
            listOf(10, 20, 50, 40, 30)
        )
    )

    levelList.add(
        Level(
            23, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2100, 2400, 2700, 3000),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            100,
            listOf(10, 40, 50, 30, 20)
        )
    )

    levelList.add(
        Level(
            24, 5,
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
            25, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2400, 2100, 3000, 2700),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            100,
            listOf(40, 50, 30, 10, 20)
        )
    )

    levelList.add(
        Level(
            26, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2400, 2100, 3000, 2700),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            4, 4,
            120,
            listOf(10, 40, 50, 30, 20)
        )
    )

    levelList.add(
        Level(
            27, 5,
            listOf(0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2400, 2100, 3000, 2700),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            120,
            listOf(10, 20, 30, 50, 40)
        )
    )

    levelList.add(
        Level(
            28, 6,
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
            29, 6,
            listOf(0f, 0f, 0f, 0f, 0f, 0f), listOf(360f, 360f, 360f, 360f, 360f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            120,
            listOf(10, 60, 30, 20, 50, 40)
        )
    )

    levelList.add(
        Level(
            30, 6,
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
            31, 6,
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
            32, 6,
            listOf(0f, 360f, 0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700, 3000, 3300),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            120,
            listOf(10, 60, 50, 40, 30, 20)
        )
    )

    levelList.add(
        Level(
            33, 6,
            listOf(0f, 360f, 0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2100, 2400, 2700, 3000, 3300),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            140,
            listOf(30, 20, 60, 40, 10, 50)
        )
    )

    levelList.add(
        Level(
            34, 6,
            listOf(0f, 360f, 0f, 360f, 0f, 360f), listOf(360f, 0f, 360f, 0f, 360f, 0f),
            listOf(1800, 2400, 2100, 3300, 2700, 3000),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            140,
            listOf(40, 20, 30, 60, 50, 10)
        )
    )

    levelList.add(
        Level(
            35, 7,
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
            36, 7,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(2100, 2400, 2700, 3000, 3300, 3600, 3900),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            150,
            listOf(70, 20, 30, 10, 50, 60, 40)
        )
    )

    levelList.add(
        Level(
            37, 7,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2100, 2400, 2700, 3000, 3300, 3600),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            180,
            listOf(10, 70, 60, 40, 50, 20, 30)
        )
    )

    levelList.add(
        Level(
            38, 7,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2400, 2100, 3300, 3600, 2700, 3000),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            180,
            listOf(40, 50, 30, 10, 70, 60, 20)
        )
    )

    levelList.add(
        Level(
            39, 7,
            listOf(0f, 360f, 0f, 360f, 0f, 360f, 0f), listOf(360f, 0f, 360f, 0f, 360f, 0f, 360f),
            listOf(1800, 2400, 2100, 3300, 3600, 2700, 3000),
            true,
            3.dp, 12.dp,
            revolvingBallsRadiusArrayFor8Tracks, trackDiameterFor8Tracks,
            colorArrayFor8Tracks,
            3, 3,
            200,
            listOf(40, 20, 30, 70, 60, 50, 10)
        )
    )

    levelList.add(
        Level(
            40,
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
            41,
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
            listOf(30, 60, 80, 40, 50, 10, 70, 20)
        )
    )

    levelList.add(
        Level(
            42,
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
            listOf(10, 60, 70, 40, 50, 80, 20, 30)
        )
    )

    levelList.add(
        Level(
            43,
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
            listOf(10, 20, 80, 40, 50, 60, 70, 30)
        )
    )

    levelList.add(
        Level(
            44,
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
            listOf(40, 20, 30, 10, 50, 80, 70, 60)
        )
    )

    levelList.add(
        Level(
            45,
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
            listOf(40, 30, 10, 20, 80, 70, 60, 50)
        )
    )

    return levelList
}
