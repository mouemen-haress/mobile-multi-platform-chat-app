package com.example.home.di

import com.example.chat.presentation.ChatViewModel
import com.example.common.data.api.repositories.ChatRepositoryImpl
import com.example.home.domain.model.repositories.ChatRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val chatKoinModule = module {
    viewModel {
        ChatViewModel(get(), get(), get())
    }
    single<ChatRepository> { ChatRepositoryImpl(get()) }
}
