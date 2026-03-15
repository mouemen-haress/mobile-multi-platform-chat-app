package com.example.onboarding.domain.model



sealed class LoginScreenChannelEvent {

    data object WorkDone : LoginScreenChannelEvent()

}