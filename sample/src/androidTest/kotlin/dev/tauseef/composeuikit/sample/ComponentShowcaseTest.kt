package dev.tauseef.composeuikit.sample

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComponentShowcaseTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun disableAutomaticAnimationClock() {
        // The sample intentionally shows an infinite loading indicator. Keep Compose's
        // test clock under test control so waitForIdle does not wait for it forever.
        composeRule.mainClock.autoAdvance = false
    }

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

    @Test
    fun disabledButton_doesNotAcceptInteraction() {
        composeRule.onNodeWithText("Disabled action").performScrollTo().assertIsNotEnabled()
    }

    @Test
    fun loadingButton_preventsRepeatedSubmission() {
        composeRule.onNodeWithText("Saving changes").performScrollTo().assertIsNotEnabled()
    }

    @Test
    fun dropdown_selectsAndDisplaysChosenOption() {
        composeRule.onNodeWithText("Category").performScrollTo()
        composeRule.onNodeWithText("Select an option").performClick()
        composeRule.onNodeWithText("Development").performClick()
        composeRule.onNodeWithText("Development").assertIsDisplayed()
    }

    @Test
    fun checkbox_updatesItsCheckedState() {
        val checkbox = composeRule.onNodeWithText("Enable notifications").performScrollTo()
        checkbox.assertIsOn()
        checkbox.performClick()
        checkbox.assertIsOff()
    }

    @Test
    fun radioGroup_selectsPhoneOption() {
        composeRule.onNodeWithText("Phone").performScrollTo().performClick().assertIsSelected()
    }

    @Test
    fun passwordField_togglesVisibilityAction() {
        composeRule.onNodeWithText("Show").performScrollTo().performClick()
        composeRule.onNodeWithText("Hide").assertIsDisplayed()
    }

    @Test
    fun searchField_clearActionResetsQuery() {
        composeRule.onNodeWithText("Search components").performScrollTo()
        composeRule.onNodeWithText("Search components").performTextInput("button")
        composeRule.onNodeWithContentDescription("Clear search").performClick()
        composeRule.onNodeWithText("Search components").assertIsDisplayed()
    }

    @Test
    fun confirmationDialog_confirmsAndShowsFeedback() {
        composeRule.onNodeWithText("Outlined action").performScrollTo().performClick()
        composeRule.onNodeWithText("Confirm action").assertIsDisplayed()
        composeRule.onNodeWithText("Confirm").performClick()
        composeRule.onNodeWithText("Confirmed").assertIsDisplayed()
    }

    @Test
    fun datePickerDialog_canBeOpenedAndDismissed() {
        composeRule.onNodeWithText("Choose date").performScrollTo().performClick()
        composeRule.onNodeWithText("Select date").assertIsDisplayed()
        composeRule.onNodeWithText("Cancel").performClick()
    }

    @Test
    fun bottomSheet_displaysCallerContentAndDismisses() {
        composeRule.onNodeWithText("Open bottom sheet").performScrollTo().performClick()
        composeRule.onNodeWithText("Reusable bottom sheet").assertIsDisplayed()
        composeRule.onNodeWithText("Close").performClick()
    }

    @Test
    fun iconButton_exposesAccessibleDescription() {
        composeRule.onNodeWithContentDescription("Show information").assertIsDisplayed()
    }

    @Test
    fun textField_displaysValidationError() {
        composeRule.onNodeWithText("Error example").performScrollTo()
        composeRule.onNodeWithText("Error example").performTextInput("invalid")
        composeRule.onNodeWithText("This value is not accepted").assertIsDisplayed()
    }

    @Test
    fun emptyState_recoveryActionShowsFeedback() {
        composeRule.onNodeWithText("Refresh").performScrollTo().performClick()
        composeRule.onNodeWithText("Refresh requested").assertIsDisplayed()
    }
}
