package com.example.common.util

import androidx.compose.ui.graphics.Color

fun convertSecondsToHoursMinutes(totalSeconds: Int): Pair<Int, Int> {
    val hours = totalSeconds / 3600
    val remainingSeconds = totalSeconds % 3600
    val minutes = remainingSeconds / 60
    return Pair(hours, minutes)
}


fun convertHexToColor(hex: String): Color {
    val cleaned = hex.removePrefix("#")
    val fullHex = if (cleaned.length == 6) "FF$cleaned" else cleaned // prepend alpha if missing
    return Color( fullHex.toLong(16).toInt())
}
