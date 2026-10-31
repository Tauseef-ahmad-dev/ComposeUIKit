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

    private fun settleUi() {
        composeRule.mainClock.advanceTimeBy(1_000)
        composeRule.waitForIdle()
    }

    @Test
    fun sample_displaysComponentPlayground() {
        composeRule.onNodeWithText("ComposeUIKit").assertIsDisplayed()
        composeRule.onNodeWithText("Buttons").assertIsDisplayed()
    }

    @Test
    fun primaryButton_displaysSnackbarFeedback() {
        composeRule.onNodeWithText("Primary action").performClick()
        settleUi()
        composeRule.onNodeWithText("Primary action clicked").assertIsDisplayed()
    }

    @Test
    fun nameField_acceptsTextInput() {
        composeRule.onNodeWithText("Full name").performScrollTo()
        composeRule.onNodeWithText("Enter your name").performTextInput("Tauseef")
        settleUi()
        composeRule.onNodeWithText("Tauseef").assertIsDisplayed()
    }

    @Test
    fun disabledButton_doesNotAcceptInteraction() {
        composeRule.onNodeWithText("Disabled action").performScrollTo()
        settleUi()
        composeRule.onNodeWithText("Disabled action").assertIsNotEnabled()
    }

    @Test
    fun loadingButton_preventsRepeatedSubmission() {
        composeRule.onNodeWithText("Saving changes").performScrollTo()
        settleUi()
        composeRule.onNodeWithText("Saving changes").assertIsNotEnabled()
    }

    @Test
    fun dropdown_selectsAndDisplaysChosenOption() {
        composeRule.onNodeWithText("Category").performScrollTo()
        composeRule.onNodeWithText("Select an option").performClick()
        settleUi()
        composeRule.onNodeWithText("Development").performClick()
        settleUi()
        composeRule.onNodeWithText("Development").assertIsDisplayed()
    }

    @Test
    fun checkbox_updatesItsCheckedState() {
        val checkbox = composeRule.onNodeWithText("Enable notifications").performScrollTo()
        checkbox.assertIsOn()
        checkbox.performClick()
        settleUi()
        checkbox.assertIsOff()
    }

    @Test
    fun radioGroup_selectsPhoneOption() {
        composeRule.onNodeWithText("Phone").performScrollTo().performClick()
        settleUi()
        composeRule.onNodeWithText("Phone").assertIsSelected()
    }

    @Test
    fun passwordField_togglesVisibilityAction() {
        composeRule.onNodeWithText("Show").performScrollTo().performClick()
        settleUi()
        composeRule.onNodeWithText("Hide").assertIsDisplayed()
    }

    @Test
    fun searchField_clearActionResetsQuery() {
        composeRule.onNodeWithText("Search components").performScrollTo()
        composeRule.onNodeWithText("Search components").performTextInput("button")
        settleUi()
        composeRule.onNodeWithContentDescription("Clear search").performClick()
        settleUi()
        composeRule.onNodeWithText("Search components").assertIsDisplayed()
    }

    @Test
    fun confirmationDialog_confirmsAndShowsFeedback() {
        composeRule.onNodeWithText("Outlined action").performScrollTo().performClick()
        settleUi()
        composeRule.onNodeWithText("Confirm action").assertIsDisplayed()
        composeRule.onNodeWithText("Confirm").performClick()
        settleUi()
        composeRule.onNodeWithText("Confirmed").assertIsDisplayed()
    }

    @Test
    fun datePickerDialog_canBeOpenedAndDismissed() {
        composeRule.onNodeWithText("Choose date").performScrollTo().performClick()
        settleUi()
        composeRule.onNodeWithText("Select date").assertIsDisplayed()
        composeRule.onNodeWithText("Cancel").performClick()
        settleUi()
    }

    @Test
    fun bottomSheet_displaysCallerContentAndDismisses() {
        composeRule.onNodeWithText("Open bottom sheet").performScrollTo().performClick()
        settleUi()
        composeRule.onNodeWithText("Reusable bottom sheet").assertIsDisplayed()
        composeRule.onNodeWithText("Close").performClick()
        settleUi()
    }

    @Test
    fun iconButton_exposesAccessibleDescription() {
        composeRule.onNodeWithContentDescription("Show information").assertIsDisplayed()
    }

    @Test
    fun textField_displaysValidationError() {
        composeRule.onNodeWithText("Error example").performScrollTo()
        composeRule.onNodeWithText("Error example").performTextInput("invalid")
        settleUi()
        composeRule.onNodeWithText("This value is not accepted").assertIsDisplayed()
    }

    @Test
    fun emptyState_recoveryActionShowsFeedback() {
        composeRule.onNodeWithText("Refresh").performScrollTo().performClick()
        settleUi()
        composeRule.onNodeWithText("Refresh requested").assertIsDisplayed()
    }
}
