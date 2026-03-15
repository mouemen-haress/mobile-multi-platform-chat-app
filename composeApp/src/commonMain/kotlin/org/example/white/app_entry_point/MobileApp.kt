package org.example.white.app_entry_point


import androidx.compose.runtime.*
import com.example.chat.presentation.ChatScreen
import com.example.common.theme.AppTheme
import com.example.navigation.RootNavigationGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext


@Composable
fun MobileApp() {
    AppTheme() {
        KoinContext {
            RootNavigationGraph()
        }
    }
}

@Preview
@Composable
fun PreviewGreeting() {
}