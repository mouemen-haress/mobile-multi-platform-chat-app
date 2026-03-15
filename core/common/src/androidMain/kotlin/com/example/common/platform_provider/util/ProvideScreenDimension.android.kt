package com.example.common.platform_provider.util

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.platform_provider.AppContext
import com.example.common.util.DeviceInfoHelper
import org.koin.core.context.GlobalContext

actual fun getScreenWidth(): Float {
    val configuration = Resources.getSystem().configuration
    return configuration.screenWidthDp.toFloat()
}

actual fun getScreenHeight(): Float {
    val configuration = Resources.getSystem().configuration
    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE && !DeviceInfoHelper.isTv()) {
        // Handle landscape orientation
        configuration.screenWidthDp.toFloat() // You might want to use screenWidthDp here for landscape
    } else {
        // Handle portrait orientation
        configuration.screenHeightDp.toFloat()
    }
}

actual fun getDeviceType(): DeviceType {
    val context = AppContext.get()
    val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    val isAndroidTV = uiModeManager.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION
    if (isAndroidTV) {
        return DeviceType.ANDROIDTV
    }
    val screenLayout = context.resources.configuration.screenLayout
    val screenSize = screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
    val isTablet = screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE
    if (isTablet) {
        if(screenLayout == Configuration.ORIENTATION_LANDSCAPE)
            return DeviceType.ANDROID_PAD_LANDSCAPE
        else return DeviceType.ANDROID_PAD
    }
    return DeviceType.ANDROID_PHONE
}

@Composable
actual fun getStatusBarHeight(): Dp {
    val context = AppContext.get()
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    val result = if (resourceId > 0) {
        context.resources.getDimensionPixelSize(resourceId)
    } else {
        0
    }
    return with(LocalDensity.current) { result.toDp() }
}


actual fun convertPxToSp(px: Float): TextUnit {
    val context = AppContext.get()
    return (px / context.resources.displayMetrics.scaledDensity).sp
}

actual fun convertPxToDp(px: Float): Dp {
    val context = AppContext.get()
    return (px / context.resources.displayMetrics.density).dp
}