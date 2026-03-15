package com.example.common.data.api

import com.example.common.data.api.dto.ChatsDto
import com.example.common.data.api.dto.LoginRequest
import com.example.common.data.api.dto.LoginResponse
import com.example.common.data.api.dto.MessageDto
import com.example.common.domain.model.Message
import com.example.common.domain.repository.PreferencesRepository
import com.example.common.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType



data class ApiServiceImpl(
    private val client: HttpClient,
    private val preferencesRepository: PreferencesRepository
) : ApiService {

    override suspend fun getAllMessages(chatId: String): List<Message> {
        return try {
            val username = preferencesRepository.getString(Constants.MY_LOGGED_IN_ID) ?: ""
            client.get("${ApiService.EndPoints.GetAllMessages.url}?chatId=$chatId")
                .body<List<MessageDto>>()
                .map { it.toMessage(username == it.senderId) }

        } catch (e: Exception) {
            println(e.message)
            emptyList<Message>()
        }
    }

    override suspend fun getUserChats(): ArrayList<ChatsDto> {
        return try {
            val requestUrl = "${ApiService.EndPoints.GetMyChats.url}?userId=${
                preferencesRepository.getString(
                    Constants.MY_LOGGED_IN_ID
                )
            }"
            client.get(requestUrl).body<ArrayList<ChatsDto>>()
        } catch (e: Exception) {
            println(e.message)
            arrayListOf<ChatsDto>()
        }
    }

    override suspend fun login(displayName: String, password: String): LoginResponse {
        val request = LoginRequest(displayName, password)
        return try {
            val response: HttpResponse = client.post(ApiService.EndPoints.Auth.url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            if (response.status == HttpStatusCode.OK) {
                response.body<LoginResponse>()
            } else {
                val errorMap = response.body<Map<String, String>>()
                LoginResponse(message = "Failed", error = errorMap["error"])
            }

        } catch (e: Exception) {
            LoginResponse(message = "Exception", error = e.message)
        }
    }

}