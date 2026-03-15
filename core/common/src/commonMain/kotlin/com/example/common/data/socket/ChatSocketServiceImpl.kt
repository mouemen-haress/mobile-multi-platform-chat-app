package org.example.white.data.remote

import com.example.common.data.api.dto.MessageDto
import com.example.common.domain.model.Message
import com.example.common.domain.model.Resource
import com.example.common.domain.repository.PreferencesRepository
import com.example.common.util.Constants

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json


class ChatSocketServiceImpl(
    private val client: HttpClient,
    private val preferencesRepository: PreferencesRepository
) : ChatSocketService {

    private var socket: WebSocketSession? = null
    private var reconnectJob: Job? = null

    override suspend fun initSession(username: String): Resource<Unit> {
        return try {
            // Retry loop until socket is active
            while (socket == null || socket?.isActive != true) {
                try {
                    socket = client.webSocketSession {
                        url("${ChatSocketService.EndPoints.ChatSocket.url}?username=$username")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    delay(3000) // wait 3 seconds before retrying
                }
            }

            if (socket?.isActive == true) {
                startReconnectWatcher(username)
                Resource.Success(Unit)
            } else {
                Resource.Error("Couldn't establish connection")
            }

        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    private fun startReconnectWatcher(username: String) {
        reconnectJob?.cancel()

        reconnectJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                if (socket?.isActive != true) {
                    try {
                        socket = client.webSocketSession {
                            url("${ChatSocketService.EndPoints.ChatSocket.url}?username=$username")
                        }
                    } catch (e: Exception) {
                    }
                }
                delay(5000)
            }
        }
    }

    override suspend fun sendMessage(message: String) {
        try {
            socket?.send(Frame.Text(message))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun observeMessage(): Flow<Message> {
        return try {
            socket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                    val username = preferencesRepository.getString(Constants.MY_LOGGED_IN_ID) ?: ""
                    val json = (it as? Frame.Text)?.readText() ?: ""
                    val messageDto = Json.decodeFromString<MessageDto>(json)
                    messageDto.toMessage(username == messageDto.senderId)
                } ?: flow {}
        } catch (e: Exception) {
            e.printStackTrace()
            flow { }
        }
    }

    override suspend fun closeConnection() {
        reconnectJob?.cancel()
        socket?.close()
    }
}
