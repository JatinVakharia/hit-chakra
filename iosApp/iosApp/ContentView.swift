import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        // get screen width and height, to revolve balls in middle of the screen
        let screen = UIScreen.main.bounds
        let screenWidth = Int32(screen.size.width)

        // Getting screenHeight by reducing camera badge and home-button height
        let window = UIApplication.shared.windows.filter {$0.isKeyWindow}.first
        let topHeight = window?.safeAreaInsets.top ?? 0.0
        let bottomHeight = window?.safeAreaInsets.bottom ?? 0.0
        let screenHeight = Int32(screen.size.height - topHeight - bottomHeight)

        return Main_iosKt.MainViewController(width: screenWidth, height: screenHeight)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



