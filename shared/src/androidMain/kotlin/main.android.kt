import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import utils.KMMContext
import utils.KMMPreference

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(applicationContext: Context) {
        val sharedPreferences = KMMPreference(applicationContext as KMMContext)
        val configuration = LocalConfiguration.current
//    setContent {
        startGame(levelList[sharedPreferences.getInt("user_level", 0)],
                sharedPreferences,
                configuration.screenWidthDp,
                configuration.screenHeightDp)
//    }
}
