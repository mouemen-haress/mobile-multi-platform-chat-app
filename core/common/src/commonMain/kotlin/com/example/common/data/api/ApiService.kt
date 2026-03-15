package com.example.common.data.api


import com.example.common.data.api.dto.ChatsDto
import com.example.common.data.api.dto.LoginResponse
import com.example.common.domain.model.Message
import com.example.common.util.Constants
import io.ktor.http.ContentType

interface ApiService {
    suspend fun getAllMessages(chatId: String): List<Message>
    suspend fun getUserChats(): ArrayList<ChatsDto>
    suspend fun login(displayName: String, password: String): LoginResponse

    companion object {
        const val BASE_URL = "http://${Constants.BASE_URL}:8080"
    }

    sealed class EndPoints(val url: String) {
        object GetAllMessages : EndPoints("$BASE_URL/messages")
        object GetMyChats : EndPoints("$BASE_URL/user-chats")
        object Auth : EndPoints("$BASE_URL/login")
    }
}