import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        // get screen width and height, to revolve balls in middle of the screen
        let screen = UIScreen.main.bounds
        let screenWidth = Int32(screen.size.width)
        let screenHeight = Int32(screen.size.height)
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



