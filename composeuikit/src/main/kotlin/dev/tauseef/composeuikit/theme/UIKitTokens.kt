package dev.tauseef.composeuikit.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Spacing and shape tokens exposed by the default ComposeUIKit theme. */
data class UIKitTokens(
    val spacing: UIKitSpacing = UIKitSpacing(),
    val cornerRadius: UIKitCornerRadius = UIKitCornerRadius()
) {
    companion object {
        val Default = UIKitTokens()
    }
}

data class UIKitSpacing(
    val xSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val xLarge: Dp = 24.dp,
    val xxLarge: Dp = 32.dp
)

data class UIKitCornerRadius(
    val small: Dp = 6.dp,
    val medium: Dp = 10.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 24.dp
)

val LocalUIKitTokens = staticCompositionLocalOf { UIKitTokens.Default }
