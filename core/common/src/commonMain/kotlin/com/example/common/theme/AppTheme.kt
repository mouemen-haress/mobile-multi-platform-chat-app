package com.example.common.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import androidx.compose.ui.text.font.FontFamily
import com.example.common.platform_provider.util.convertPxToDp
import com.example.common.util.DeviceInfoHelper
import fourthColor

import org.jetbrains.compose.resources.Font
import primaryColor
import secondaryColor
import thirdColor
import whitelabel.core.common.generated.resources.*

private val darkColorScheme = AppColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    third = thirdColor,
    fourth = fourthColor
)

private val lightColorScheme = AppColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    third = thirdColor,
    fourth = fourthColor
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun GetBebasFontFamily() = FontFamily(Font(Res.font.bebas_nue_regular))


@Composable
fun type() = AppTypography(
    titleLarge = titleLarge(),
    titleNormal = headlineMedium(),
    titlePrimary = titlePrimary(),
    body = labelBody(),
    labelLarge = headlineMedium(),
    labelNormal = labelNormal(),
    labelSmall = headlineSmall(),
    buttonText = headLineButton()
)

private val shape = AppShape(
    container = RoundedCornerShape(30.dp),
    button = RoundedCornerShape(50)
)

private val margin = AppMargin(
    small = if (DeviceInfoHelper.isTv()) {
        12.dp
    } else {
        8.dp
    },
    large = if (DeviceInfoHelper.isTv()) {
        32.dp
    } else {
        24.dp
    },
    templateRowGap = if (DeviceInfoHelper.isTv()) {
        convertPxToDp(48f)
    } else if (DeviceInfoHelper.isTablet()) {
        48.dp
    } else {
        40.dp
    }

)
private val size = AppSize(
    large = 20.dp,
    medium = 16.dp,
    normal = 12.dp,
    small = 8.dp,
    icon = if (DeviceInfoHelper.isMobile()) {
        20.dp
    } else {
        32.dp
    },
    tvMenuWidth = 330.dp,
    tvMenuHeight = 1020.dp

)


@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme

    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppShape provides shape,
        LocalAppSize provides size,
        LocalAppMargin provides margin,
        LocalAppTypography provides type(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(colorScheme.primary)
        ) {
            content()
        }

    }
}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current

    val size: AppSize
        @Composable get() = LocalAppSize.current

    val margin: AppMargin
        @Composable get() = LocalAppMargin.current

    val type: AppTypography
        @Composable get() = LocalAppTypography.current
}