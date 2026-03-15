package com.example.common.data.api.repositories

import com.example.common.data.api.ApiService
import com.example.common.data.api.dto.ChatsDto
import com.example.home.domain.model.repositories.ChatListRepository


class ChatListRepositoryImpl(
    private val apiService: ApiService,
) : ChatListRepository {


    override suspend fun getUserChats(): ArrayList<ChatsDto> {
        return apiService.getUserChats()
    }
}