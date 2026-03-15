package com.example.common.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val displayName: String,
    val password: String
)
