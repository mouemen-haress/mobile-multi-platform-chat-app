package com.example.common.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.devx.kdeviceinfo.DeviceInfoXState
import com.example.common.domain.model.DeviceInfo
import com.example.common.platform_provider.util.DeviceType
import com.example.common.platform_provider.util.getDeviceType


object DeviceInfoHelper {
    fun getDeviceInfo(): DeviceInfo {
        val deviceInfoXState: DeviceInfoXState = DeviceInfoXState()
        var deviceInfo = DeviceInfo()
        if (deviceInfoXState.isAndroid) {
            val deviceInfoObj = deviceInfoXState.androidInfo
            deviceInfo.deviceName = deviceInfoObj.device
            deviceInfo.deviceType = "OTT"
            deviceInfo.physicalDeviceId = deviceInfoObj.androidId
            deviceInfo.caDeviceId = deviceInfoObj.androidId
            deviceInfo.caDeviceType = "CASTLAB"
            deviceInfo.deviceModel = if (isTv()) "tv" else {
                "mobile"
            }
            deviceInfo.deviceSoftware = "android"
            deviceInfo.manufacturer = deviceInfoObj.manufacturer
            deviceInfo.serialNumber = "Unknown"
            deviceInfo.applicationVersion =
                "${deviceInfoObj.versionName}.${deviceInfoObj.versionCode}"
            deviceInfo.deviceSoftwareVersion = deviceInfoObj.version.sdkInt.toString()
            deviceInfo.middlewareVersion = deviceInfoObj.versionName
        } else if (deviceInfoXState.isIos) {
            val deviceInfoObj = deviceInfoXState.iosInfo
            deviceInfo.deviceName = deviceInfoObj.systemName
            deviceInfo.deviceType = "OTT"
            deviceInfo.physicalDeviceId = deviceInfoObj.bundleId
            deviceInfo.caDeviceId = deviceInfoObj.bundleId
            deviceInfo.caDeviceType = "CASTLAB"
            deviceInfo.deviceModel = "mobile"
            deviceInfo.deviceSoftware = "ios"
            deviceInfo.manufacturer = "apple"
            deviceInfo.serialNumber = "Unknown"
            deviceInfo.applicationVersion = deviceInfoObj.appVersion
            deviceInfo.deviceSoftwareVersion = deviceInfoObj.systemVersion
            deviceInfo.middlewareVersion = deviceInfoObj.appVersion
        }
        return deviceInfo
    }

    @Composable
    fun getStatusBarHeight(): Dp {
        return com.example.common.platform_provider.util.getStatusBarHeight()
    }

    fun isMobile(): Boolean {
        return getDeviceType() == DeviceType.ANDROID_PHONE || getDeviceType() == DeviceType.IPHONE
    }

    fun isTablet(): Boolean {
        return getDeviceType() == DeviceType.IPAD || getDeviceType() == DeviceType.ANDROID_PAD
    }

    fun isTabletLandscape():Boolean {
        return getDeviceType() == DeviceType.IPAD_LANDSCAPE || getDeviceType() == DeviceType.ANDROID_PAD_LANDSCAPE
    }
    fun isTv(): Boolean {
        return getDeviceType() == DeviceType.ANDROIDTV

    }

}