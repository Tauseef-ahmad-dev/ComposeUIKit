package dev.tauseef.composeuikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val LightColorScheme = lightColorScheme(
    primary = UIKitColors.Primary,
    onPrimary = UIKitColors.OnPrimary,
    secondary = UIKitColors.Secondary,
    background = UIKitColors.LightBackground,
    surface = UIKitColors.LightSurface,
    error = UIKitColors.Error
)

private val DarkColorScheme = darkColorScheme(
    primary = UIKitColors.PrimaryDark,
    onPrimary = UIKitColors.OnPrimary,
    secondary = UIKitColors.SecondaryDark,
    background = UIKitColors.DarkBackground,
    surface = UIKitColors.DarkSurface,
    error = UIKitColors.Error
)

/**
 * Provides ComposeUIKit's default Material 3 theme and design tokens.
 *
 * Supply [colorScheme], [typography], [shapes], or [tokens] to adapt the library
 * to an application's design system. If [colorScheme] is omitted, a light or
 * dark default is selected using [darkTheme].
 */
@Composable
fun UIKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorScheme: ColorScheme? = null,
    typography: Typography = UIKitTypography,
    shapes: Shapes = UIKitShapes,
    tokens: UIKitTokens = UIKitTokens.Default,
    content: @Composable () -> Unit
) {
    val resolvedColorScheme = colorScheme ?: if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    CompositionLocalProvider(LocalUIKitTokens provides tokens) {
        MaterialTheme(
            colorScheme = resolvedColorScheme,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}
