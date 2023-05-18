package utils

import kotlinx.cinterop.CValue
import platform.CoreGraphics.CGRect
import platform.UIKit.UIScreen

actual fun getScreenWidthPx() : Int {
    // Todo Jatin Verify below code to get width
    val screenSize : CValue<CGRect> = UIScreen.mainScreen().bounds
//    return screenSize.align
    return 0
}

actual fun getScreenHeightPx() : Int {
    val screenSize : CValue<CGRect> = UIScreen.mainScreen().bounds
//    return screenSize.align
    return 0
}