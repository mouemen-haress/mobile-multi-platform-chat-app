package com.example.home.di

import com.example.common.data.api.repositories.ChatListRepositoryImpl
import com.example.home.domain.model.repositories.ChatListRepository
import com.example.home.presentation.ChatsListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val chatsListKoinModule = module {
    viewModel {
        ChatsListViewModel(get())
    }
    single<ChatListRepository> { ChatListRepositoryImpl(get()) }
}
