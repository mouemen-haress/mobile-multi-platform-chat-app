package com.example.chat.domain.model

import com.example.common.domain.model.Message


data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val messageText: String = ""
)