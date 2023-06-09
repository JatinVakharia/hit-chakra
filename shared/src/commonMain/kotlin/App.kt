import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mu.KotlinLogging
import theme.HitChakraTheme
import ui.ConfettiCenterView
import ui.PointsAnimation
import utils.KMMPreference

val levelList = getLevelObjects()
private val logger = KotlinLogging.logger {}
lateinit var sharedPreferences: KMMPreference
lateinit var levelObj: Level
var screenWidthInDp: Int = 0
var screenHeightInDp: Int = 0

expect fun getPlatformName(): String

@Composable
fun startGame(
    level: Level,
    sharedPreference: KMMPreference,
    screenWidthDp: Int,
    screenHeightDp: Int
) {
    levelObj = level
    sharedPreferences = sharedPreference
    screenWidthInDp = screenWidthDp
    screenHeightInDp = screenHeightDp
    // Handles Win and Loss of game
    var gameState = remember { mutableStateOf(State.Playing) }
    // Handles next level or try again of game
    var gameBehaviour = remember { mutableStateOf(Behaviour.None) }
    // Handles state of level by managing lives and points
    var levelState = remember { mutableStateOf(-1) }

    HitChakraTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            BallsRevolving(level, levelState, screenWidthDp, screenHeightDp)
        }
    }
    observeGameState(gameState, gameBehaviour)
    observeGameBehaviour(level, gameBehaviour)
    observeLevelState(level, levelState, gameState)
}

@Composable
fun observeLevelState(level: Level, levelState: MutableState<Int>, gameState: MutableState<State>) {

    val stateValue = levelState.value
    if (stateValue == 0) {
        PointsAnimation()
        modifyGameState(level, gameState)
    } else if (stateValue > 0) {
        ConfettiCenterView("$stateValue")
        level.pointsEarned += stateValue
        modifyGameState(level, gameState)
    }
}

@Composable
private fun modifyGameState(level: Level, gameState: MutableState<State>) {
    rememberCoroutineScope().launch {
        // added this delay to give room to points animation
        delay(2000)
        // Reduce a life of current level
        level.livesRemaining--

        // Todo Jatin Make this condition equal to 0, to harden the level
        if ((level.pointsToWin - level.pointsEarned) <= 0) {
            gameState.value = State.Win
        } else if (level.livesRemaining == 0) {
            gameState.value = State.Loss
        } else
            gameState.value = State.NextAttempt
    }
}

@Composable
private fun observeGameBehaviour(level: Level, gameBehaviour: MutableState<Behaviour>) {
    when (gameBehaviour.value) {
        Behaviour.NextLevel -> {
            startGame(
                levelList[sharedPreferences.getInt("user_level", 0)],
                sharedPreferences,
                screenWidthInDp,
                screenHeightInDp
            )
        }

        Behaviour.Retry -> {
            level.livesRemaining = level.livesAllotted
            level.pointsEarned = 0
            startGame(
                level,
                sharedPreferences,
                screenWidthInDp,
                screenHeightInDp
            )
        }

        Behaviour.SameLevelNextAttempt -> {
            startGame(
                level,
                sharedPreferences,
                screenWidthInDp,
                screenHeightInDp
            )
        }

        Behaviour.AddOneLife -> {
            level.livesRemaining++
            startGame(
                level,
                sharedPreferences,
                screenWidthInDp,
                screenHeightInDp
            )
        }

        else -> {}
    }
}

@Composable
private fun observeGameState(
    gameState: MutableState<State>,
    gameBehaviour: MutableState<Behaviour>
) {
    // Handles show and hide of dialog
    var dialogState = remember { mutableStateOf(true) }
    if (gameState.value == State.Loss) {
        logger.debug { "You Loss" }
        if (dialogState.value)
            createDialogContent(
                openDialogCustom = dialogState,
                State.Loss,
                actionFunction = { tryAgainSameLevel(gameBehaviour) },
                { watchAds(gameBehaviour) }
            )
    } else if (gameState.value == State.Win) {
        logger.debug { "You Win" }
        if (dialogState.value)
            createDialogContent(
                openDialogCustom = dialogState,
                State.Win,
                actionFunction = { moveToNextLevel(gameBehaviour) },
                { watchAds(gameBehaviour) }
            )
    } else if (gameState.value == State.NextAttempt) {
        logger.debug { "Next Attempt" }
        gameBehaviour.value = Behaviour.SameLevelNextAttempt
    }
}

private fun exitApp() {
//    finish()
}

expect fun watchAds(gameBehaviour: MutableState<Behaviour>)

private fun moveToNextLevel(gameBehaviour: MutableState<Behaviour>) {
    clearData()
    // Increment level
    val userLevel = sharedPreferences.getInt("user_level", 0)
    if (userLevel < levelList.size - 1)
        sharedPreferences.put("user_level", userLevel + 1)

    // Move to next level
    gameBehaviour.value = Behaviour.NextLevel
}

fun addOneLife(gameBehaviour: MutableState<Behaviour>) {
    // Add one life/attempt to current level
    gameBehaviour.value = Behaviour.AddOneLife
}

private fun tryAgainSameLevel(gameBehaviour: MutableState<Behaviour>) {
    clearData()
    gameBehaviour.value = Behaviour.Retry
}