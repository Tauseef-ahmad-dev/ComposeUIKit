package dev.tauseef.composeuikit

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import dev.tauseef.composeuikit.theme.UIKitColors
import org.junit.Assert.assertTrue
import org.junit.Test

class UIKitColorsTest {
    @Test
    fun primaryForegroundsMeetWcagAaContrast() {
        assertContrastAtLeast(
            background = UIKitColors.Primary,
            foreground = UIKitColors.OnPrimary
        )
        assertContrastAtLeast(
            background = UIKitColors.PrimaryDark,
            foreground = UIKitColors.OnPrimaryDark
        )
    }

    private fun assertContrastAtLeast(
        background: Color,
        foreground: Color,
        minimum: Float = 4.5f
    ) {
        val backgroundLuminance = background.luminance()
        val foregroundLuminance = foreground.luminance()
        val lighter = maxOf(backgroundLuminance, foregroundLuminance)
        val darker = minOf(backgroundLuminance, foregroundLuminance)
        val contrastRatio = (lighter + 0.05f) / (darker + 0.05f)

        assertTrue(
            "Expected contrast ratio >= $minimum but was $contrastRatio",
            contrastRatio >= minimum
        )
    }
}
