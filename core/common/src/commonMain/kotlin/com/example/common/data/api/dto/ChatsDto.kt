package com.example.common.data.api.dto

import com.example.common.domain.model.Chat
import com.example.common.util.DateHelper
import kotlinx.serialization.Serializable


@Serializable
data class ChatsDto(
    val id: String,
    val type: ChatType,
    val members: List<String>,
    val name: String? = null,
    val lastMesage: String? = null,
    val createdAt: Long
) {

    fun toChat(chatsDto: ChatsDto): Chat {
        return Chat(
            id = chatsDto.id,
            name = chatsDto.name ?: "",
            lastMessage = lastMesage ?: "",
            time = DateHelper.formatTimestamp(chatsDto.createdAt)
        )
    }
}

@Serializable
enum class ChatType {
    SINGLLE,
    GROUP
}
