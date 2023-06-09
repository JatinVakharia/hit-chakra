import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.ComposeUIViewController
import platform.darwin.NSObject
import utils.KMMPreference

actual fun getPlatformName(): String = "iOS"

fun MainViewController(width: Int, height: Int) = ComposeUIViewController {
    // for shared preference
    val sharedPreferences = KMMPreference(NSObject())

    startGame(
        levelList[sharedPreferences.getInt("user_level", 0)],
        sharedPreferences,
        width,
        height
    )
}

actual fun watchAds(gameBehaviour: MutableState<Behaviour>) {

}