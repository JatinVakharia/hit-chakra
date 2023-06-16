import UIKit
import SwiftUI
import shared
import Firebase
import GoogleMobileAds

private var rewardedAd: GADRewardedAd?
private var uiController: UIViewController?
private var adConfig: AdConfig?

struct ComposeView: UIViewControllerRepresentable {

    func makeUIViewController(context: Context) -> UIViewController {

        adConfig = AdConfig()

        // Initialize Firebase SDK.
        FirebaseApp.configure()

        // Initialize the Google Mobile Ads SDK.
        GADMobileAds.sharedInstance().start(completionHandler: {_ in
            adConfig?.loadRewardedAd()
        })

        // get screen width and height, to revolve balls in middle of the screen
        let screen = UIScreen.main.bounds
        let screenWidth = Int32(screen.size.width)

        // Getting screenHeight by reducing camera badge and home-button height
        let window = UIApplication.shared.windows.filter {$0.isKeyWindow}.first
        let topHeight = window?.safeAreaInsets.top ?? 0.0
        let bottomHeight = window?.safeAreaInsets.bottom ?? 0.0
        let screenHeight = Int32(screen.size.height - topHeight - bottomHeight)

        // Custom Crash to test Crashlytics
        // fatalError("Crash was triggered")

        uiController = Main_iosKt.MainViewController(width: screenWidth, height: screenHeight, adConfig : adConfig as! AdsDelegate)

        return uiController!
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

class AdConfig : AdsDelegate {

    func loadRewardedAd() {
            let request = GADRequest()
            let adUnitID = (Main_iosKt.isDebug ? "ca-app-pub-3940256099942544/1712485313" : "ca-app-pub-1929641051704456/5505495313")
            GADRewardedAd.load(withAdUnitID: adUnitID,
                                 request: request,
                                 completionHandler: { [weak self] ad, error in
                if let error = error {
                  print("Failed to load rewarded ad with error: \(error.localizedDescription)")
                  return
                }
                rewardedAd = ad
                print("Rewarded ad loaded.")
              }
            )
        }

    func showAd() {
        loadRewardedAd()
        print("showAd")
        if let ad = rewardedAd {
            ad.present(fromRootViewController: uiController!) {
            let reward = ad.adReward
            print("Reward received with currency \(reward.amount), amount \(reward.amount.doubleValue)")
            Main_iosKt.addLife()
          }
        } else {
          print("Ad wasn't ready")
        }
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



