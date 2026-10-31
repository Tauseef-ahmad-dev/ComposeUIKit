package dev.tauseef.composeuikit.sample

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComponentShowcaseTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun sample_displaysComponentPlayground() {
        composeRule.onNodeWithText("ComposeUIKit").assertIsDisplayed()
        composeRule.onNodeWithText("Buttons").assertIsDisplayed()
    }

    @Test
    fun primaryButton_displaysSnackbarFeedback() {
        composeRule.onNodeWithText("Primary action").performClick()
        composeRule.onNodeWithText("Primary action clicked").assertIsDisplayed()
    }

    @Test
    fun nameField_acceptsTextInput() {
        composeRule.onNodeWithText("Full name").performScrollTo()
        composeRule.onNodeWithText("Enter your name").performTextInput("Tauseef")
        composeRule.onNodeWithText("Tauseef").assertIsDisplayed()
    }
}
