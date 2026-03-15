package com.example.common.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.text.TextStyle
import primaryColor
import secondaryColor
import thirdColor


data class AppColorScheme(
    val primary: Color,
    val secondary: Color,
    val third: Color,
    val fourth: Color
)

data class AppTypography(
    val titleLarge: TextStyle,
    val titleNormal: TextStyle,
    val titlePrimary: TextStyle,
    val body: TextStyle,
    val labelLarge: TextStyle,
    val labelNormal: TextStyle,
    val labelSmall: TextStyle,
    val buttonText: TextStyle
)

data class AppShape(
    val container: Shape,
    val button: Shape,
)

data class AppSize(
    val large: Dp,
    val medium: Dp,
    val normal: Dp,
    val small: Dp,
    val icon: Dp,
    val tvMenuWidth: Dp,
    val tvMenuHeight: Dp,
)

data class AppMargin(
    val small: Dp,
    val large: Dp,
    val templateRowGap: Dp
)

val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
    )
}


val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleLarge = TextStyle.Default,
        titleNormal = TextStyle.Default,
        body = TextStyle.Default,
        labelLarge = TextStyle.Default,
        labelSmall = TextStyle.Default,
        labelNormal = TextStyle.Default,
        buttonText = TextStyle.Default,
        titlePrimary = TextStyle.Default
    )
}


val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        RectangleShape, RectangleShape
    )
}
val LocalAppMargin = staticCompositionLocalOf {
    AppMargin(
        small = Dp.Unspecified,
        large = Dp.Unspecified,
        templateRowGap = Dp.Unspecified
    )
}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        large = Dp.Unspecified,
        medium = Dp.Unspecified,
        normal = Dp.Unspecified,
        small = Dp.Unspecified,
        icon = Dp.Unspecified,
        tvMenuHeight = Dp.Unspecified,
        tvMenuWidth = Dp.Unspecified
    )

}


