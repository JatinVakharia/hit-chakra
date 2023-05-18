import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import utils.KMMContext
import utils.KMMPreference

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(applicationContext: Context) {
        // for shared preference
        val sharedPreferences = KMMPreference(applicationContext as KMMContext)
        // get screen width and height, to revolve balls in middle of the screen
        val configuration = LocalConfiguration.current
        startGame(levelList[sharedPreferences.getInt("user_level", 0)],
                sharedPreferences,
                configuration.screenWidthDp,
                configuration.screenHeightDp)
}
