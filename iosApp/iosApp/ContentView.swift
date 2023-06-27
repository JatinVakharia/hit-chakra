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
          print("The rewarded ad wasn't ready yet.")
          // Added Life to keep game going
          Main_iosKt.addLife()
//           showToast("Ad wasn't ready!")
          Crashlytics.crashlytics().log("The rewarded ad wasn't ready yet.")
        }
    }
}

extension UIViewController {

    func showToast(message : String) {

        let toastLabel = UILabel(frame: CGRect(x: self.view.frame.size.width/2 - 75, y: self.view.frame.size.height-100, width: 150, height: 35))
        toastLabel.backgroundColor = UIColor.black.withAlphaComponent(0.6)
        toastLabel.textColor = UIColor.white
        toastLabel.font = .systemFont(ofSize: 12.0)
        toastLabel.textAlignment = .center;
        toastLabel.text = message
        toastLabel.alpha = 1.0
        toastLabel.layer.cornerRadius = 10;
        toastLabel.clipsToBounds  =  true
        self.view.addSubview(toastLabel)
        UIView.animate(withDuration: 4.0, delay: 0.1, options: .curveEaseOut, animations: {
             toastLabel.alpha = 0.0
        }, completion: {(isCompleted) in
            toastLabel.removeFromSuperview()
        })
    }

}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



