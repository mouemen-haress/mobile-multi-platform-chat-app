package com.example.chat.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.domain.repository.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.example.chat.domain.model.ChatState
import com.example.common.data.api.ApiService
import com.example.common.data.api.dto.MessageDto
import com.example.common.util.Constants
import com.example.common.util.DateHelper
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.white.data.remote.ChatSocketService


class ChatViewModel(
    val preferencesRepository: PreferencesRepository,
    val chatSocketService: ChatSocketService,
    private val apiService: ApiService,
) : ViewModel() {



    private val _state = MutableStateFlow<ChatState>(ChatState())
    val state: StateFlow<ChatState> = _state

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    fun connectToChat(chatId: String) {
        getAllMessages(chatId)
        viewModelScope.launch {
            chatSocketService.observeMessage().onEach { message ->
                val newList = state.value.messages.toMutableList().apply {
                    add(0, message)
                }
                _state.value = state.value.copy(
                    messages = newList
                )
            }.launchIn(viewModelScope)
        }
    }

    fun onMessageChange(message: String) {
        _state.update {
            it.copy(messageText = message)
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            chatSocketService.closeConnection()
        }
    }

    fun getAllMessages(chatId: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val result = apiService.getAllMessages(chatId)
            _state.value = state.value.copy(
                messages = result,
                isLoading = false
            )
        }
    }

    fun sendMessage(chatId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val username = preferencesRepository.getString(Constants.MY_LOGGED_IN_ID)
            if (!username.isNullOrEmpty()) {
                if (state.value.messageText.isNotBlank()) {

                    chatSocketService.sendMessage(
                        Json.encodeToString(
                            MessageDto(
                                chatId = chatId,
                                senderId = username,
                                text = state.value.messageText,
                                createdAt = DateHelper.getCurrentTime()
                            )
                        )
                    )
                    withContext(Dispatchers.Main) {
                        onMessageChange("")
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }
}

