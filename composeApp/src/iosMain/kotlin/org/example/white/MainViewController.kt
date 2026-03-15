package org.example.white

import androidx.compose.ui.window.ComposeUIViewController
import org.example.white.app_entry_point.MobileApp
import org.example.white.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) {
    MobileApp()
}