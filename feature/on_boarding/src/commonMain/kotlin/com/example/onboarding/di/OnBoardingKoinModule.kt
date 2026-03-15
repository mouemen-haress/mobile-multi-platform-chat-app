package com.example.onboarding.di

import com.example.onboarding.domain.repositories.AuthRepository
import com.example.onboarding.presentation.login.LoginViewModel
import org.example.white.data.remote.repositories.AuthRepositoryImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val onBoardingKoinModule = module {
    viewModel {
        LoginViewModel(get(), get(), get())
    }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}
