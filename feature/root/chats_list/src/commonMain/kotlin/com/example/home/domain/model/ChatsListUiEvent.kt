package com.example.home.domain.model

sealed class ChatsListUiEvent {
    object fetchMyChatsList : ChatsListUiEvent()
}