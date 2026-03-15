package org.example.white.data.remote.repositories

import com.example.common.data.api.ApiService
import com.example.common.data.api.dto.LoginResponse
import com.example.onboarding.domain.repositories.AuthRepository

class AuthRepositoryImpl(
    private val apiService: ApiService,
) : AuthRepository {

    override suspend fun login(displayName: String, password: String): LoginResponse {
        return apiService.login(displayName, password)
    }
}