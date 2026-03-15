package com.example.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.model.ChatsListState
import com.example.home.domain.model.repositories.ChatListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ChatsListViewModel(
    val chatListRepository: ChatListRepository
) : ViewModel() {

    private val _usersState = MutableStateFlow<ChatsListState>(ChatsListState())
    val usersState: StateFlow<ChatsListState> = _usersState


    fun fetchMyChatsList() {
        _usersState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            val chatList = chatListRepository.getUserChats()
            _usersState.update {
                it.copy(
                    chatsList = chatList.map {
                        it.toChat(it)
                    }.toCollection(ArrayList()),
                    isLoading = false
                )
            }
        }
    }


}

