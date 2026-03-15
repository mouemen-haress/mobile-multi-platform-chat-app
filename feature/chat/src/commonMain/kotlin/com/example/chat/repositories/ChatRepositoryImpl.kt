package com.example.common.data.api.repositories

import com.example.common.data.api.ApiService
import com.example.common.data.api.dto.ChatsDto
import com.example.home.domain.model.repositories.ChatRepository


class ChatRepositoryImpl(
    private val apiService: ApiService,
) : ChatRepository {


    override suspend fun getUserChats(): ArrayList<ChatsDto> {
        return apiService.getUserChats()
    }
}