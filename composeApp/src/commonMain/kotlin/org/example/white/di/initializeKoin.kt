package org.example.white.di

import com.example.common.di.commonAppModule
import com.example.home.di.chatKoinModule
import com.example.home.di.chatsListKoinModule
import com.example.navigation.di.navigationKoinModule
import com.example.onboarding.di.onBoardingKoinModule
import com.example.root.di.rootKoinModule
import org.koin.core.context.startKoin

fun initializeKoin() {
    startKoin {
        modules(
            onBoardingKoinModule,
            commonAppModule,
            rootKoinModule,
            navigationKoinModule,
            chatsListKoinModule,
            chatKoinModule
        )
    }
}