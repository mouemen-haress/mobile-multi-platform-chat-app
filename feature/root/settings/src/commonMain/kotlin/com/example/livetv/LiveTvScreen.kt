package com.example.livetv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun LiveTvScreen(navigateToDetails: (Int) -> Unit) {
    //val screenModel = navigator?.koinNavigatorScreenModel<SharedConfigViewModel>()



//        BoxWithConstraints {
//            if (maxWidth < 600.dp) {
//            } else {
//            }
//        }
    LaunchedEffect(Unit) {
//            viewModel.channnel.collect { event ->
//                when (event) {
//
//                }
//            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),

        ) {
            Text("Bonjour In Live Tv", modifier = Modifier.clickable {
            })
    }
}