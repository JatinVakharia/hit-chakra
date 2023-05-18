import androidx.compose.ui.window.ComposeUIViewController
import platform.CoreGraphics.CGRect
import platform.UIKit.UIScreen
import platform.darwin.NSObject
import utils.KMMPreference

actual fun getPlatformName(): String = "iOS"

fun MainViewController(width: Int, height: Int) = ComposeUIViewController {
//    App()
    val sharedPreferences = KMMPreference(NSObject())
//    val screenRect = UIScreen.mainScreen.bounds.size

    startGame(
        levelList[sharedPreferences.getInt("user_level", 0)],
        sharedPreferences,
        width,
        height
    )
}