package com.example.common.domain.model

import org.jetbrains.compose.resources.DrawableResource

data class TopNavigationItems(
    val rank: Int,
    val title: String,
    val icon: DrawableResource?,
)
