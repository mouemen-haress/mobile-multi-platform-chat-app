package com.example.common.di

import com.example.common.data.api.ApiService
import com.example.common.data.api.ApiServiceImpl
import com.example.common.data.local.PreferencesRepositoryImpl
import com.example.common.domain.repository.PreferencesRepository
import com.example.common.domain.use_case.ConnectToSocketUseCase
import com.example.common.platform_provider.provideHttpClient
import com.russhwolf.settings.Settings
import io.ktor.client.HttpClient
import org.example.white.data.remote.ChatSocketService
import org.example.white.data.remote.ChatSocketServiceImpl
import org.koin.dsl.module

val commonAppModule = module {
    single { Settings() }
    single<PreferencesRepository> { PreferencesRepositoryImpl(settings = get()) }


    single<HttpClient> {
        provideHttpClient(get())
    }

    single<ApiService> { ApiServiceImpl(get(), get()) }
    single { ConnectToSocketUseCase(get(), get()) }
    single<ChatSocketService> { ChatSocketServiceImpl(get(), get()) }


}
