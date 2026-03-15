package com.example.home.domain.model.repositories

import com.example.common.data.api.dto.ChatsDto


interface ChatListRepository {

    open suspend fun getUserChats(): ArrayList<ChatsDto>

}