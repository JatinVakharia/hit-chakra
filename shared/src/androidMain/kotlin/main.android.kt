import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalConfiguration
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import utils.KMMContext
import utils.KMMPreference

private var rewardedAd: RewardedAd? = null
private var mainActivity: Activity? = null
private const val TAG = "MainActivity"
actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(activity: Activity) {
        mainActivity = activity
        // for shared preference
        val sharedPreferences = KMMPreference(activity.applicationContext as KMMContext)
        // get screen width and height, to revolve balls in middle of the screen
        val configuration = LocalConfiguration.current

        // Initialize ads
        // Todo apply check for EU
        MobileAds.initialize(activity.applicationContext) {
                // onInitializationComplete
                loadAd(activity)
        }

        // Start Game for the first time
        startGame(levelList[sharedPreferences.getInt("user_level", 0)],
                sharedPreferences,
                configuration.screenWidthDp,
                configuration.screenHeightDp)
}

private fun loadAd(activity: Activity) {
        var adRequest = AdRequest.Builder().build()
        // Todo change adUnitId at the time of production release :: ca-app-pub-1929641051704456/7259289052
        RewardedAd.load(
                activity.applicationContext,
                "ca-app-pub-3940256099942544/5224354917",
                adRequest,
                object : RewardedAdLoadCallback() {
                        override fun onAdFailedToLoad(adError: LoadAdError) {
                                adError?.toString()?.let { Log.d(TAG, it) }
                                rewardedAd = null
                        }

                        override fun onAdLoaded(ad: RewardedAd) {
                                Log.d(TAG, "Ad was loaded.")
                                rewardedAd = ad
                        }
                })
}

actual fun watchAds(gameBehaviour: MutableState<Behaviour>){
        mainActivity?.let { loadAd(it) }
        rewardedAd?.let { ad ->
                mainActivity?.let { activity ->
                        ad.show(activity, OnUserEarnedRewardListener { rewardItem ->
                                // Handle the reward.
                                val rewardAmount = rewardItem.amount
                                val rewardType = rewardItem.type
                                Log.d(TAG, "User earned the reward.")
                                Log.d(TAG, "rewardAmount $rewardAmount")
                                Log.d(TAG, "rewardType $rewardType")
                                addOneLife(gameBehaviour)
                        })
                }
        } ?: run {
                Log.d(TAG, "The rewarded ad wasn't ready yet.")
        }
}
