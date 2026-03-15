package org.example.white.data.remote

import com.example.common.domain.model.Message
import com.example.common.domain.model.Resource
import com.example.common.util.Constants
import kotlinx.coroutines.flow.Flow
interface ChatSocketService {

    suspend fun initSession(username: String): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessage(): Flow<Message>

    suspend fun closeConnection()

    companion object {
        const val BASE_URL = "ws://${Constants.BASE_URL}:8080"
    }

    sealed class EndPoints(val url:String){
        object ChatSocket:EndPoints("$BASE_URL/chat-socket")
    }
}