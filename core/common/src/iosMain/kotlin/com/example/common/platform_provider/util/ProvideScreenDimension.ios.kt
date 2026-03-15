package com.example.common.platform_provider.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devx.kdeviceinfo.model.common.DeviceOrientation
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIScreen
import platform.UIKit.UIDevice
import platform.CoreGraphics.CGRectGetWidth
import platform.CoreGraphics.CGRectGetHeight
import platform.UIKit.UIDeviceOrientation
import platform.UIKit.UIDeviceOrientationIsLandscape
import platform.UIKit.UIUserInterfaceIdiomPad
import platform.UIKit.UIUserInterfaceIdiomTV

@OptIn(ExperimentalForeignApi::class)
actual fun getScreenWidth(): Float {
    val bounds = UIScreen.mainScreen.bounds
    return CGRectGetWidth(bounds).toFloat()
}

@OptIn(ExperimentalForeignApi::class)
actual fun getScreenHeight(): Float {
    val bounds = UIScreen.mainScreen.bounds
//    return CGRectGetHeight(bounds).toFloat()
    return if (UIDeviceOrientationIsLandscape(UIDeviceOrientation.UIDeviceOrientationPortrait)) {
        minOf(CGRectGetHeight(bounds).toFloat(), getScreenWidth())
    } else {
        maxOf(CGRectGetHeight(bounds).toFloat(), getScreenWidth())
    }

}

actual fun getDeviceType(): DeviceType {
    if (UIDevice.currentDevice.userInterfaceIdiom == UIUserInterfaceIdiomPad) {
        if (UIDeviceOrientationIsLandscape(UIDeviceOrientation.UIDeviceOrientationPortrait)) {
            return DeviceType.IPAD_LANDSCAPE
        } else {
            return DeviceType.IPAD
        }
    }
    if (UIDevice.currentDevice.userInterfaceIdiom == UIUserInterfaceIdiomTV) {
        return DeviceType.TVOS
    }
    return DeviceType.IPHONE
}

@Composable
actual fun getStatusBarHeight(): Dp {
    return 0.dp
}

actual fun convertPxToSp(px: Float): TextUnit {
    return px.sp
}

actual fun convertPxToDp(px: Float): Dp {
    return px.dp
}