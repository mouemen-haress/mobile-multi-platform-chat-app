package com.example.common.platform_provider
import platform.Foundation.NSBundle
import platform.UIKit.UIApplication


class IOSPlatform: Platform {
    override val name: String = "IOS"
}

actual fun getPlatform(): Platform = IOSPlatform()

actual object AppContext
