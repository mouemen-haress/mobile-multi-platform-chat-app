package com.example.common.platform_provider

import com.example.common.domain.repository.PreferencesRepository
import com.example.common.util.Constants
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


actual fun provideHttpClient(preferencesRepository: PreferencesRepository): HttpClient =
    HttpClient(Darwin) {
        install(WebSockets)
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

    }.also {
        Napier.base(DebugAntilog())
        it.plugin(HttpSend).intercept { request ->
            val modifiedRequest = request
            execute(modifiedRequest)
        }
    }
