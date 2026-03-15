package com.example.onboarding.domain.repositories

import com.example.common.data.api.dto.LoginResponse

interface AuthRepository {

    open suspend fun login(displayName: String, password: String): LoginResponse
}