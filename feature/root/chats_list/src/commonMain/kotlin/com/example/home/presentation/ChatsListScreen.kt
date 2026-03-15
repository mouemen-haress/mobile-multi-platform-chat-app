package com.example.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.common.theme.AppTheme
import com.example.home.domain.model.ChatsListState
import com.example.home.domain.model.ChatsListUiEvent
import org.example.white.presentation.chat.ChatItem

@Composable
fun ChatsListScreen(
    state: ChatsListState,
    navigateToChat: (id: String) -> Unit,
    onEvent: (chatsListUiEvent: ChatsListUiEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(ChatsListUiEvent.fetchMyChatsList)
    }


    Box(modifier = Modifier.fillMaxSize()) {

        // LazyColumn always at the top
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.chatsList) { chat ->
                ChatItem(chat) {
                    navigateToChat(chat.id)
                }
            }
        }

        // Centered Loader over the screen
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = AppTheme.colorScheme.third
                )
            }
        }
    }
}
