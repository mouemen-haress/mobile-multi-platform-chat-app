package com.example.common.domain.repository

interface PreferencesRepository {
    suspend fun getString(key: String): String
    suspend fun setString(key: String, value: String)

    suspend fun getBoolean(key: String): Boolean
    suspend fun setBoolean(key: String, value: Boolean)
}