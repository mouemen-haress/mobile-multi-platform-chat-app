package com.example.onboarding.domain.model


sealed class ConfigUiEvent {
    object startBootContentProcess : ConfigUiEvent()
}
