package utils

import android.util.DisplayMetrics

actual fun getScreenWidthPx() : Int {
    val displayMetrics = DisplayMetrics()
    return displayMetrics.widthPixels
}

actual fun getScreenHeightPx() : Int {
    val displayMetrics = DisplayMetrics()
    return displayMetrics.heightPixels
}