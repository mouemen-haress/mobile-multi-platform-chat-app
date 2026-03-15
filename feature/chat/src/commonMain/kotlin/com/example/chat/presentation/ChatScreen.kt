package com.example.chat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chat.domain.model.ChatState
import com.example.chat.domain.model.ChatUiEvent
import org.example.white.presentation.common.MessageItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChatScreen(
    state: ChatState,
    onEvent: (chatUiEvent: ChatUiEvent) -> Unit
) {
    val selectedMessageIds = remember { mutableStateListOf<String>() }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
            items(state.messages) { message ->
                val isOwnMessage = message.isItMyMessage
                val isSelected = selectedMessageIds.contains(message.id)

                MessageItem(
                    isOwnMessage,
                    message.senderName,
                    message.text,
                    message.createdAt.toString(),
                    onToggleSelect = {
                        if (isSelected) selectedMessageIds.remove(message.id)
                        else message.id?.let {
                            selectedMessageIds.add(it)
                            println()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }


        MessageInputArea()

//        Row(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            TextField(
//                value = state.messageText,
//                onValueChange = { onEvent(ChatUiEvent.OnMessageChange(it)) },
//                placeholder = {
//                    Text(text = "أدخل رسالتك")
//                },
//                modifier = Modifier.weight(1f)
//            )
//            IconButton(onClick = { onEvent(ChatUiEvent.SendMessage) }) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.Send,
//                    contentDescription = "Send"
//                )
//            }
//        }

    }
}

@Preview
@Composable
fun PreviewGreeting() {
    ChatScreen(ChatState(), { })
}