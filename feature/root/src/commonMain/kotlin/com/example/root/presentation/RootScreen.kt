package com.example.root.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.common.presentation.TopBar
import com.example.common.theme.AppTheme
import com.example.root.navigation.BottomBarNavigationGraph
import metallic_silver
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen() {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val backstackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationRoute by remember {
        derivedStateOf {
            backstackEntry?.destination?.route
        }
    }
    val isTopBarVisible by remember {
        derivedStateOf {
            currentDestinationRoute?.contains("Details") == false
        }
    }
    val isBottomBarVisible by remember {
        derivedStateOf {
            currentDestinationRoute?.contains("com.example.common.Screen.Screen.Chat/{id}") == false
        }
    }
    val rootScreenViewModel = koinViewModel<RootScreenViewModel>()

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(initialOffsetY = { -it }),
                exit = slideOutVertically(targetOffsetY = { -it })
            ) {
                TopBar(topList = rootScreenViewModel.getAppTopBar()) {

                }
            }
        },
        bottomBar = {
            if (isBottomBarVisible) {
                NavigationBar(
                    containerColor = AppTheme.colorScheme.primary,
                    contentColor = Color.White,
                    modifier = Modifier.shadow(
                        elevation = 24.dp,
                        spotColor = Color(0xC10B091F),
                        ambientColor = Color(0xC10B091F)
                    ),
                    tonalElevation = 8.dp
                ) {
                    rootScreenViewModel.getAppMenu().forEach { destination ->
                        val isItemSelected = checkIfItemSelected(
                            currentDestinationRoute = currentDestinationRoute,
                            bottomBarDestination = destination.screen.toString()
                        )
                        NavigationBarItem(
                            selected = isItemSelected,
                            label = {
                                Text(
                                    destination.label,
                                    style = AppTheme.type.labelNormal,
                                    color = if (isItemSelected)
                                        AppTheme.colorScheme.secondary
                                    else metallic_silver
                                )
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(destination.icon),
                                    contentDescription = null,
                                    modifier = Modifier.height(AppTheme.size.icon).width(
                                        AppTheme.size.icon
                                    ),
                                    tint = if (isItemSelected)
                                        AppTheme.colorScheme.secondary
                                    else Color.White
                                )
                            },
                            onClick = {
                                navController.navigate(destination.screen) {
                                    popUpTo(navController.graph.findStartDestination().route!!) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults
                                .colors(
                                    selectedIconColor = AppTheme.colorScheme.primary,
                                    indicatorColor = Color.Transparent
                                )
                        )

                    }
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.background(AppTheme.colorScheme.secondary)) {
            val animatedTopPadding by animateDpAsState(
                targetValue = if (isTopBarVisible) padding.calculateTopPadding() else 0.dp,
                animationSpec = tween(durationMillis = 300)
            )
            val modifiedPadding = PaddingValues(
                start = padding.calculateStartPadding(LocalLayoutDirection.current),
                top = animatedTopPadding,
                end = padding.calculateEndPadding(LocalLayoutDirection.current),
                bottom = padding.calculateBottomPadding()
            )
            BottomBarNavigationGraph(
                navController = navController,
                paddingValues = modifiedPadding
            )
        }
    }
}


private fun checkIfItemSelected(
    bottomBarDestination: String?,
    currentDestinationRoute: String?,
): Boolean {
    return if ((currentDestinationRoute?.contains("Home") == true || currentDestinationRoute?.contains(
            "Details"
        ) == true) && bottomBarDestination?.contains("Home") == true
    ) true
    else if (currentDestinationRoute?.contains(bottomBarDestination.toString()) == true) true
    else false
}