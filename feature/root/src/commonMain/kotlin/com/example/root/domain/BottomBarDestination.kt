package com.example.root.domain


import whitelabel.core.common.generated.resources.*
import com.example.common.Screen.Screen
import org.jetbrains.compose.resources.DrawableResource



data class BottomBarDestination(
    val screen: Screen,
    val label: String,
    val icon: DrawableResource = Res.drawable.picto,
)

val defaultBottomBarDestinations = arrayListOf(
    BottomBarDestination(
        screen = Screen.ChatsList,
        label = "Chats List",
        icon = Res.drawable.picto
    ),


    BottomBarDestination(
        screen = Screen.Settings,
        label = "Settings",
        icon = Res.drawable.livetv
    )
)