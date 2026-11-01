package dev.tauseef.composeuikit.sample

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComponentShowcaseTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

        composeRule.waitForIdle()
    }

    /**
     * Scroll the LazyColumn itself to an item before querying its descendants.
     * LazyColumn only composes visible items, so calling performScrollTo() on an
     * off-screen descendant cannot work: that node does not exist in the tree yet.
     */
    private fun scrollTo(text: String) {
        composeRule.onNodeWithTag("component-playground-list")
            .performScrollToNode(hasText(text))
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
        scrollTo("Full name")
        composeRule.onNodeWithTag("sample-name-field").performTextInput("Tauseef")
        composeRule.onNodeWithText("Tauseef").assertIsDisplayed()
    }

    @Test
    fun disabledButton_doesNotAcceptInteraction() {
        scrollTo("Disabled action")
        composeRule.onNodeWithText("Disabled action").assertIsNotEnabled()
    }

    @Test
    fun loadingButton_preventsRepeatedSubmission() {
        scrollTo("Saving changes")
        composeRule.onNodeWithText("Saving changes").assertIsNotEnabled()
    }

    @Test
    fun dropdown_selectsAndDisplaysChosenOption() {
        scrollTo("Select an option")
        composeRule.onNodeWithText("Select an option").performClick()
        composeRule.onNodeWithText("Development").performClick()
        composeRule.onNodeWithText("Development").assertIsDisplayed()
    }

    @Test
    fun checkbox_updatesItsCheckedState() {
        scrollTo("Enable notifications")
        val checkbox = composeRule.onNodeWithText("Enable notifications")
        checkbox.assertIsOn()
        checkbox.performClick()
        checkbox.assertIsOff()
    }

    @Test
    fun radioGroup_selectsPhoneOption() {
        scrollTo("Phone")
        composeRule.onNodeWithText("Phone").performClick()
        composeRule.onNodeWithText("Phone").assertIsSelected()
    }

    @Test
    fun passwordField_togglesVisibilityAction() {
        scrollTo("Show")
        composeRule.onNodeWithText("Show").performClick()
        composeRule.onNodeWithText("Hide").assertIsDisplayed()
    }

    @Test
    fun searchField_clearActionResetsQuery() {
        scrollTo("Search components")
        composeRule.onNodeWithText("Search components").performTextInput("button")
        composeRule.onNodeWithContentDescription("Clear search").performClick()
        composeRule.onNodeWithText("Search components").assertIsDisplayed()
    }

    @Test
    fun confirmationDialog_confirmsAndShowsFeedback() {
        scrollTo("Outlined action")
        composeRule.onNodeWithText("Outlined action").performClick()
        composeRule.onNodeWithText("Confirm action").assertIsDisplayed()
        composeRule.onNodeWithText("Confirm").performClick()
        composeRule.onNodeWithText("Confirmed").assertIsDisplayed()
    }

    @Test
    fun datePickerDialog_canBeOpenedAndDismissed() {
        scrollTo("Choose date")
        composeRule.onNodeWithText("Choose date").performClick()
        composeRule.onNodeWithText("Select date").assertIsDisplayed()
        composeRule.onNodeWithText("Cancel").performClick()
    }

    @Test
    fun bottomSheet_displaysCallerContentAndDismisses() {
        scrollTo("Open bottom sheet")
        composeRule.onNodeWithText("Open bottom sheet").performClick()
        composeRule.onNodeWithText("Reusable bottom sheet").assertIsDisplayed()
        composeRule.onNodeWithText("Close").performClick()
    }

    @Test
    fun iconButton_exposesAccessibleDescription() {
        composeRule.onNodeWithContentDescription("Show information").assertIsDisplayed()
    }

    @Test
    fun textField_displaysValidationError() {
        scrollTo("Error example")
        composeRule.onNodeWithText("Error example").performTextInput("invalid")
        composeRule.onNodeWithText("This value is not accepted").assertIsDisplayed()
    }

    @Test
    fun emptyState_recoveryActionShowsFeedback() {
        scrollTo("Refresh")
        composeRule.onNodeWithText("Refresh").performClick()
        composeRule.onNodeWithText("Refresh requested").assertIsDisplayed()
    }
}
