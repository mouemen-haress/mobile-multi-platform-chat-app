package com.example.common.theme

import TextWhite
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import whitelabel.core.common.generated.resources.*
import com.example.common.platform_provider.util.DeviceType
import com.example.common.platform_provider.util.convertPxToSp
import com.example.common.platform_provider.util.getDeviceType

import com.example.common.util.DeviceInfoHelper
import org.jetbrains.compose.resources.Font
import secondaryColor
import textCaption


@Composable
fun headlineMedium(): TextStyle {
    val density = LocalDensity.current
    var lineHeight = 32.sp
    var textSize = 24.sp * density.fontScale
    if (DeviceInfoHelper.isTablet()) {
        textSize = 32.sp * density.fontScale
        lineHeight = 40.sp
    } else if (DeviceInfoHelper.isTv()) {
        textSize = 40.sp * density.fontScale
        lineHeight = 48.sp
    }
    return TextStyle(
        fontWeight = FontWeight(600),
        fontSize = textSize,
        lineHeight = lineHeight,
        fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
        color = Color.White
    )

}

@Composable
fun headlineSmall(): TextStyle {
    val density = LocalDensity.current
    var textSize = 10.sp * density.fontScale
    return TextStyle(
        fontWeight = FontWeight(400),
        fontSize = textSize,
        fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
        color = textCaption
    )

}

@Composable
fun headLineButton(): TextStyle {
    val density = LocalDensity.current
    var textSize = 16.sp * density.fontScale
    var lineHeight = 20.sp
    if (DeviceInfoHelper.isTv()) {
        textSize = convertPxToSp(32f) * density.fontScale
        lineHeight = convertPxToSp(44f)
    } else if (DeviceInfoHelper.isTablet()) {
        textSize = 20.sp * density.fontScale
        lineHeight = 28.sp
    }
    return TextStyle(
        fontWeight = FontWeight(400),
        fontSize = textSize,
        lineHeight = lineHeight,
        color = Color.White,
        fontFamily = FontFamily(Font(Res.font.Poppins_Regular))
    )
}

@Composable
fun labelNormal(): TextStyle {
    val density = LocalDensity.current
    var textSize = 12.sp * density.fontScale
    var lineHeight = 16.sp
    if (DeviceInfoHelper.isTv()) {
        textSize = convertPxToSp(24f) * density.fontScale
        lineHeight = convertPxToSp(32f)
    } else if (DeviceInfoHelper.isTablet()) {
        textSize = 14.sp * density.fontScale
        lineHeight = 20.sp
    }

    return TextStyle(
        fontWeight = FontWeight(400),
        fontSize = textSize,
        lineHeight = lineHeight,
        color = Color.White,
        fontFamily = FontFamily(Font(Res.font.Poppins_Light))
    )

}

@Composable
fun labelBody(): TextStyle {
    val density = LocalDensity.current
    var textSize = 14.sp * density.fontScale
    var lineHeight = 16.sp
    if (DeviceInfoHelper.isTv()) {
        textSize = convertPxToSp(28f) * density.fontScale
        lineHeight = convertPxToSp(32f)
    } else if (DeviceInfoHelper.isTablet()) {
        textSize = 16.sp * density.fontScale
        lineHeight = 20.sp
    }

    return TextStyle(
        fontWeight = FontWeight(400),
        fontSize = textSize,
        lineHeight = lineHeight,
        color = TextWhite,
        fontFamily = FontFamily(Font(Res.font.Poppins_Light))
    )

}


@Composable
fun titlePrimary(): TextStyle {
    val density = LocalDensity.current
    var textSize = 24.sp * density.fontScale
    var lineHeight = 20.sp
    if (DeviceInfoHelper.isTv()) {
        textSize = convertPxToSp(32f) * density.fontScale
        lineHeight = convertPxToSp(44f)
    } else if (DeviceInfoHelper.isTablet()) {
        textSize = 20.sp * density.fontScale
        lineHeight = 28.sp
    }

    return TextStyle(
        fontWeight = FontWeight(600),
        fontSize = textSize,
        lineHeight = lineHeight,
        fontFamily = FontFamily(Font(Res.font.Poppins_Medium)),
        color = secondaryColor,
    )

}

@Composable
fun titleLarge(): TextStyle {
    val density = LocalDensity.current
    var textSize = 16.sp * density.fontScale
    var lineHeight = 20.sp
    if (DeviceInfoHelper.isTv()) {
        textSize = convertPxToSp(32f) * density.fontScale
        lineHeight = convertPxToSp(44f)
    } else if (DeviceInfoHelper.isTablet()) {
        textSize = 20.sp * density.fontScale
        lineHeight = 28.sp
    }

    return TextStyle(
        fontWeight = FontWeight(600),
        fontSize = textSize,
        lineHeight = lineHeight,
        fontFamily = FontFamily(Font(Res.font.Poppins_Medium)),
        color = AppTheme.colorScheme.secondary,
    )

}