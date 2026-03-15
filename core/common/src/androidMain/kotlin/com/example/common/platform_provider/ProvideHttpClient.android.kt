package com.example.common.platform_provider

import com.example.common.domain.repository.PreferencesRepository
import com.example.common.util.Constants
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin

actual fun provideHttpClient(preferencesRepository: PreferencesRepository): HttpClient =
    HttpClient(CIO) {
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
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    when {
                        message.startsWith("[ERROR]") -> Napier.e(message, tag = "Ktor")
                        message.startsWith("[WARN]") -> Napier.w(message, tag = "Ktor")
                        message.startsWith("[INFO]") -> Napier.i(message, tag = "Ktor")
                        else -> {
                            Napier.d(message, tag = "Ktor")
                        }
                    }
                }
            }
            level = LogLevel.ALL
        }
    }.also {
        Napier.base(DebugAntilog())
        it.plugin(HttpSend).intercept { request ->
            val modifiedRequest = request
            execute(modifiedRequest)
        }
    }
