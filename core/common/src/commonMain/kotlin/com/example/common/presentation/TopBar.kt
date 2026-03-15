package com.example.common.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.common.domain.model.TopNavigationItems
import com.example.common.theme.AppTheme


import org.jetbrains.compose.resources.painterResource
import whitelabel.core.common.generated.resources.*



@Composable
fun TopBar(topList: List<TopNavigationItems>, onClick: (String) -> Unit) {
    val gradientColors =
        listOf(AppTheme.colorScheme.primary, AppTheme.colorScheme.secondary)
    val statusBarHeight = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding()

    Row(
        modifier = Modifier.height(70.dp).fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors
                ),
            )
            .padding(top = statusBarHeight),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.logo), contentDescription = "logo",
            modifier = Modifier.width(56.dp).height(32.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.weight(1f))
        val topIcons = topList.sortedBy { it.rank }
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (item in topIcons) {
                IconButton(
                    enabled = true,
                    onClick = {
                        onClick(item.title)
                    },
                ) {
                    Icon(
                        painter = painterResource(item.icon!!), contentDescription = item.title,
                        modifier = Modifier.width(20.dp).height(20.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }

    }
}

