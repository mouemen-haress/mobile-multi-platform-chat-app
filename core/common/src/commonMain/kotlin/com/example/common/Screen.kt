package com.example.common.Screen

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object OnBoardingGraph : Screen()

    @Serializable
    data object Login : Screen()

    @Serializable
    data object Root : Screen()

    @Serializable
    data object ChatsListGraph : Screen()


    @Serializable
    data object ChatsList : Screen()

    @Serializable
    data class Chat(val id: String) : Screen()

    @Serializable
    data object Settings : Screen()


    @Serializable
    data object Halaqat : Screen()
}