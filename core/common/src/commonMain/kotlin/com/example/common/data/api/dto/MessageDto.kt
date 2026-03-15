package com.example.common.data.api.dto

import com.example.common.domain.model.Message
import com.example.common.util.DateHelper
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val chatId: String,
    val senderId: String,
    val senderName: String? = null,
    val text: String,
    val type: MessageType = MessageType.TEXT,
    val createdAt: Long,
    val id: String? = null
) {
    fun toMessage(isItMyMessage: Boolean): Message {
        return Message(
            text = text,
            createdAt = DateHelper.formatTimestamp(createdAt),
            senderName = senderName,
            isItMyMessage = isItMyMessage,
            senderId = senderId,
            id = id
        )
    }

}

@Serializable
enum class MessageType {
    TEXT,
    MEDIA
}