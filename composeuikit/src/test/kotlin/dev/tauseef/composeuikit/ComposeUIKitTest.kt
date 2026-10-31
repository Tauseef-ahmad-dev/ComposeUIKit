package dev.tauseef.composeuikit

import org.junit.Assert.assertEquals
import org.junit.Test

class ComposeUIKitTest {
    @Test
    fun version_isInitialDevelopmentRelease() {
        assertEquals("0.1.0", ComposeUIKit.VERSION)
    }
}
