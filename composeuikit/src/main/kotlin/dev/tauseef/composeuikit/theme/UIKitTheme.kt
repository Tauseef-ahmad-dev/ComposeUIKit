package dev.tauseef.composeuikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
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
 * Applications may continue to use their own MaterialTheme outside this wrapper.
 */
@Composable
fun UIKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = UIKitTypography,
        shapes = UIKitShapes,
        content = {
            CompositionLocalProvider(
                LocalUIKitTokens provides UIKitTokens.Default,
                content = content
            )
        }
    )
}
