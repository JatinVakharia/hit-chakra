import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.ComposeUIViewController
import delegate.AdsDelegate
import platform.darwin.NSObject
import utils.KMMPreference

actual fun getPlatformName(): String = "iOS"
private lateinit var gameBehaviourLocal: MutableState<Behaviour>
private lateinit var adConfigLocal: AdsDelegate
actual val isDebug = Platform.isDebugBinary

fun MainViewController(width: Int, height: Int, adConfig: AdsDelegate) = ComposeUIViewController {

    adConfigLocal = adConfig

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
    gameBehaviourLocal = gameBehaviour
    adConfigLocal.showAd()
}

fun addLife(){
    addOneLife(gameBehaviourLocal)
}

actual fun playMissedSound() {

}

actual fun playWinSound() {

}