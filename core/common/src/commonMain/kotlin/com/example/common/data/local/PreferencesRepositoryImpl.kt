package com.example.common.data.local

import com.russhwolf.settings.Settings
import com.example.common.domain.repository.PreferencesRepository
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings

class PreferencesRepositoryImpl(
    settings: Settings,
) : PreferencesRepository {
    companion object {
        const val TIMESTAMP_KEY = "lastUpdated"
    }

    private val flowSettings: FlowSettings = (settings as ObservableSettings).toFlowSettings()


    override suspend fun getString(key: String): String {
        return flowSettings.getString(
            key, ""
        )
    }

    override suspend fun setString(key: String, value: String) {
        flowSettings.putString(
            key = key,
            value = value
        )
    }

    override suspend fun getBoolean(key: String): Boolean {
        return flowSettings.getBoolean(
            key, false
        )
    }

    override suspend fun setBoolean(key: String, value: Boolean) {
        return flowSettings.putBoolean(
            key, value
        )
    }

}