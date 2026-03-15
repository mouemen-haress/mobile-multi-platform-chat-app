package com.example.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.common.domain.repository.PreferencesRepository
import com.example.common.util.Constants
import com.example.navigation.domain.SharedChannelEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class SharedViewModel(
    val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    private val _uiChannel = Channel<SharedChannelEvent>()
    val uiChannel = _uiChannel.receiveAsFlow()






}