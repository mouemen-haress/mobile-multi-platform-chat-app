package com.example.navigation.di

import com.example.navigation.SharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val navigationKoinModule = module {
    viewModel {
        SharedViewModel(get())
    }
}
