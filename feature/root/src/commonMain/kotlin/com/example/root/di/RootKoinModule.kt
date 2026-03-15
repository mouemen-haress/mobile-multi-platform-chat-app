package com.example.root.di

import com.example.root.presentation.RootScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val rootKoinModule = module {
    viewModel {
        RootScreenViewModel()
    }
}
