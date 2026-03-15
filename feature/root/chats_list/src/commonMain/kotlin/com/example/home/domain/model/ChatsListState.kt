package com.example.home.domain.model

import com.example.common.domain.model.Chat


data class ChatsListState(
    val isLoading: Boolean = false,
    val chatsList: ArrayList<Chat> = arrayListOf())