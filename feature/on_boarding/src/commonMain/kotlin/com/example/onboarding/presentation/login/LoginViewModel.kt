package com.example.onboarding.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.domain.model.Resource
import com.example.common.domain.repository.PreferencesRepository
import com.example.common.domain.use_case.ConnectToSocketUseCase
import com.example.common.util.Constants
import com.example.onboarding.domain.model.LoginState
import com.example.onboarding.domain.repositories.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LoginViewModel(
    val authRepository: AuthRepository,
    val preferencesRepository: PreferencesRepository,
    val connectToSocketUseCase: ConnectToSocketUseCase
) : ViewModel() {

    private val _uiEvent = Channel<LoginUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _loginState = MutableStateFlow<LoginState>(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun login(displayName: String, password: String) {
        viewModelScope.launch {
            val resp = authRepository.login(displayName, password)
            if (resp.userId != null) {
                preferencesRepository.setString(Constants.MY_LOGGED_IN_ID, resp.userId!!)
                when (connectToSocketUseCase.invoke()) {
                    is Resource.Error<*> -> {
                        _loginState.update {
                            it.copy(
                                dialogText = "socket connection error"
                            )
                        }
                    }

                    is Resource.Success<*> -> {
                        _uiEvent.send(LoginUiEvent.Success)
                    }
                }


            } else {
                _loginState.update {
                    it.copy(
                        dialogText = "Log in error : invalid password or username"
                    )
                }
            }
        }
    }
}