package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.common.Screen.Screen
import com.example.navigation.domain.SharedChannelEvent
import com.example.onboarding.presentation.HalaqatScreen
import com.example.onboarding.presentation.login.LoginScreen
import com.example.onboarding.presentation.login.LoginUiEvent
import com.example.onboarding.presentation.login.LoginViewModel
import com.example.root.presentation.RootScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RootNavigationGraph() {
    val navController = rememberNavController()

    val sharedViewModel = koinViewModel<SharedViewModel>()
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            sharedViewModel.uiChannel.collect { event ->

            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = Screen.OnBoardingGraph
    ) {
        composable<Screen.Root> {
            RootScreen()
        }


        navigation<Screen.OnBoardingGraph>(
            startDestination = Screen.Halaqat
        ) {
            composable<Screen.Halaqat> {
//                val splashViewModel = koinViewModel<SplashScreenViewModel>()
                val lifecycleOwner = LocalLifecycleOwner.current

                LaunchedEffect(Unit) {
                    lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                    }
                }
                HalaqatScreen() {
                    navController.navigate(Screen.Login)
                }
            }

            composable<Screen.Login> {
                val loginViewModel = koinViewModel<LoginViewModel>()
                val lifecycleOwner = LocalLifecycleOwner.current

                LaunchedEffect(Unit) {
                    lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        loginViewModel.uiEvent.collect {
                            when (it) {
                                LoginUiEvent.Success -> {
                                    navController.navigate(Screen.Root)
                                }
                            }
                        }
                    }
                }
                LoginScreen(
                    loginViewModel.loginState.collectAsState().value
                ) { email, password ->
                    loginViewModel.login(email, password)
                }
            }
        }


    }

}

