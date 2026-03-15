package com.example.common.platform_provider.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit


expect fun getScreenWidth(): Float

expect fun getScreenHeight(): Float

@Composable
expect fun getStatusBarHeight(): Dp

expect fun getDeviceType(): DeviceType

expect fun convertPxToSp(px: Float): TextUnit

expect fun convertPxToDp(px: Float): Dp

// commonMain
enum class DeviceType {
    IPAD,
    TVOS,
    IPHONE,
    ANDROIDTV,
    ANDROID_PHONE,
    ANDROID_PAD,
    ANDROID_PAD_LANDSCAPE,
    IPAD_LANDSCAPE
}
