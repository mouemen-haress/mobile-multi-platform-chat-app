package com.example.common.domain.use_case

import com.example.common.domain.model.Resource
import com.example.common.domain.repository.PreferencesRepository
import com.example.common.util.Constants
import org.example.white.data.remote.ChatSocketService

class ConnectToSocketUseCase(
    val socketService: ChatSocketService,
    val preferencesRepository: PreferencesRepository
) {

    suspend fun invoke(): Resource<Unit> {
        return connectToSocket()
    }

    private suspend fun connectToSocket(): Resource<Unit> {
        val username = preferencesRepository.getString(
            Constants.MY_LOGGED_IN_ID
        ) ?: ""
        return socketService.initSession(username)

    }
}