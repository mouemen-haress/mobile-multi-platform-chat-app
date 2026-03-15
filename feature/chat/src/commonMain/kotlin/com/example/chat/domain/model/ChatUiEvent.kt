package com.example.chat.domain.model

sealed class ChatUiEvent() {
    data class OnMessageChange(val text: String) : ChatUiEvent()
    object SendMessage : ChatUiEvent()


}
