package dev.tauseef.composeuikit

import androidx.compose.ui.unit.dp
import dev.tauseef.composeuikit.theme.UIKitTokens
import org.junit.Assert.assertEquals
import org.junit.Test

class UIKitTokensTest {
    @Test
    fun defaultSpacingTokens_matchDesignScale() {
        val spacing = UIKitTokens.Default.spacing

        assertEquals(4.dp, spacing.xSmall)
        assertEquals(8.dp, spacing.small)
        assertEquals(12.dp, spacing.medium)
        assertEquals(16.dp, spacing.large)
        assertEquals(24.dp, spacing.xLarge)
        assertEquals(32.dp, spacing.xxLarge)
    }

    @Test
    fun defaultCornerRadii_matchDesignScale() {
        val radius = UIKitTokens.Default.cornerRadius

        assertEquals(6.dp, radius.small)
        assertEquals(10.dp, radius.medium)
        assertEquals(16.dp, radius.large)
        assertEquals(24.dp, radius.extraLarge)
    }
}
