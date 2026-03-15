package com.example.common.platform_provider

import com.example.common.domain.repository.PreferencesRepository
import io.ktor.client.HttpClient

expect fun provideHttpClient(preferencesRepository: PreferencesRepository): HttpClient