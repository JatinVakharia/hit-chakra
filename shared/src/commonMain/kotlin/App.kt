import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import mu.KotlinLogging
import theme.HitChakraTheme
import ui.PointsAnimation
import utils.KMMPreference

val levelList = getLevelObjects()
private val logger = KotlinLogging.logger {}
lateinit var sharedPreferences : KMMPreference
var screenWidthInDp : Int = 0
var screenHeightInDp : Int = 0

expect fun getPlatformName(): String
@Composable
fun startGame(
    level: Level,
    sharedPreference: KMMPreference,
    screenWidthDp: Int,
    screenHeightDp: Int
) {
    sharedPreferences = sharedPreference
    screenWidthInDp = screenWidthDp
    screenHeightInDp = screenHeightDp
    // Handles Win and Loss of game
    var gameState = remember { mutableStateOf(State.InProgress) }
    // Handles next level or try again of game
    var gameBehaviour = remember { mutableStateOf(Behaviour.None) }

    HitChakraTheme (darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            BallsRevolving(level, gameState, screenWidthDp, screenHeightDp)
        }
    }
    observeGameState(gameState, gameBehaviour)
    observeGameBehaviour(gameBehaviour)
}

@Composable
private fun observeGameBehaviour(gameBehaviour: MutableState<Behaviour>) {
    if (gameBehaviour.value == Behaviour.Retry) {
        startGame(
            levelList[sharedPreferences.getInt("user_level", 0)],
            sharedPreferences,
            screenWidthInDp,
            screenHeightInDp
        )
    } else if (gameBehaviour.value == Behaviour.NextLevel) {
        startGame(
            levelList[sharedPreferences.getInt("user_level", 0)],
            sharedPreferences,
            screenWidthInDp,
            screenHeightInDp
        )
    }
}

@Composable
private fun observeGameState(
    gameState: MutableState<State>,
    gameBehaviour: MutableState<Behaviour>
) {
    // Handles show and hide of dialog
    var dialogState = remember { mutableStateOf(true) }
    // Todo Jatin Add showDialog functions for Android and iOS
    if (gameState.value == State.Loss) {
        logger.debug { "You Loss" }
        if (dialogState.value)
            createDialogContent(
                openDialogCustom = dialogState,
                State.Loss,
                actionFunction = { tryAgainSameLevel(gameBehaviour) },
                ::exitApp
            )
        // Todo remove : temp testing
        PointsAnimation("0")
    } else if (gameState.value == State.Win) {
        logger.debug { "You Win" }
        if (dialogState.value)
            createDialogContent(
                openDialogCustom = dialogState,
                State.Win,
                actionFunction = { moveToNextLevel(gameBehaviour) },
                ::exitApp
            )
        // Todo remove : temp testing
        PointsAnimation("100")
    }
}

private fun exitApp() {
//    finish()
}

private fun moveToNextLevel(gameBehaviour: MutableState<Behaviour>) {
    clearData()
    // Increment level
    val userLevel = sharedPreferences.getInt("user_level", 0)
    if(userLevel < levelList.size - 1)
        sharedPreferences.put("user_level", userLevel + 1)

    // Move to next level
    gameBehaviour.value = Behaviour.NextLevel
}

private fun tryAgainSameLevel(gameBehaviour: MutableState<Behaviour>) {
    clearData()
    gameBehaviour.value = Behaviour.Retry
}