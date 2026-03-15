package com.example.root.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.chat.domain.model.ChatUiEvent
import com.example.chat.presentation.ChatScreen
import com.example.chat.presentation.ChatViewModel
import com.example.common.Screen.Screen
import com.example.home.domain.model.ChatsListUiEvent
import com.example.home.presentation.ChatsListScreen
import com.example.home.presentation.ChatsListViewModel
import com.example.livetv.LiveTvScreen
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BottomBarNavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding(),
            ),
        navController = navController,
        startDestination = Screen.ChatsListGraph
    ) {
        navigation<Screen.ChatsListGraph>(
            startDestination = Screen.ChatsList
        ) {
            composable<Screen.ChatsList>(enterTransition = { null }, exitTransition = { null }) {
                val chatsListViewModel = koinViewModel<ChatsListViewModel>()
                val lifecycleOwner = LocalLifecycleOwner.current

                LaunchedEffect(Unit) {
                    lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                    }
                }

                ChatsListScreen(
                    chatsListViewModel.usersState.collectAsState().value,
                    navigateToChat = { id -> navController.navigate(Screen.Chat(id)) }
                ) {
                    when (it) {
                        ChatsListUiEvent.fetchMyChatsList -> {
                            chatsListViewModel.fetchMyChatsList()
                        }
                    }
                }

            }

            composable<Screen.Chat> {
                val chatViewModel = koinViewModel<ChatViewModel>()
                val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
                val chatId = it.toRoute<Screen.Chat>().id
                DisposableEffect(key1 = lifecycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_START) {
                            chatViewModel.connectToChat(chatId)
                        } else if (event == Lifecycle.Event.ON_STOP) {
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }

                ChatScreen(
                    chatViewModel.state.collectAsState().value
                ) {
                    when (it) {
                        is ChatUiEvent.OnMessageChange -> {
                            chatViewModel.onMessageChange(it.text)
                        }

                        ChatUiEvent.SendMessage -> {
                            chatViewModel.sendMessage(chatId)
                        }
                    }
                }
            }
        }


        composable<Screen.Settings>(enterTransition = { null }, exitTransition = { null }) {
            LiveTvScreen {}
        }
    }
}