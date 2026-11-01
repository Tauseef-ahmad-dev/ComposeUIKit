package dev.tauseef.composeuikit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.tauseef.composeuikit.components.buttons.UIKitButton
import dev.tauseef.composeuikit.components.buttons.UIKitButtonVariant
import dev.tauseef.composeuikit.components.buttons.UIKitIconButton
import dev.tauseef.composeuikit.components.dialogs.UIKitConfirmationDialog
import dev.tauseef.composeuikit.components.sheets.UIKitBottomSheet
import dev.tauseef.composeuikit.components.layout.UIKitDivider
import dev.tauseef.composeuikit.components.feedback.UIKitEmptyState
import dev.tauseef.composeuikit.components.feedback.UIKitSnackbarHost
import dev.tauseef.composeuikit.components.inputs.UIKitPasswordField
import dev.tauseef.composeuikit.components.inputs.UIKitSearchField
import dev.tauseef.composeuikit.components.inputs.UIKitTextField
import dev.tauseef.composeuikit.components.layout.UIKitCard
import dev.tauseef.composeuikit.components.selection.UIKitCheckbox
import dev.tauseef.composeuikit.components.selection.UIKitDatePickerDialog
import dev.tauseef.composeuikit.components.selection.UIKitDropdownField
import dev.tauseef.composeuikit.components.selection.UIKitFilterChip
import dev.tauseef.composeuikit.components.selection.UIKitRadioGroup
import dev.tauseef.composeuikit.theme.UIKitTheme
import java.text.DateFormat
import java.util.Date
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIKitTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()

                var name by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }
                var query by rememberSaveable { mutableStateOf("") }
                var category by rememberSaveable { mutableStateOf<String?>(null) }
                var selectedFilter by rememberSaveable { mutableStateOf("All") }
                var notificationsEnabled by rememberSaveable { mutableStateOf(true) }
                var deliveryMethod by rememberSaveable { mutableStateOf("Email") }
                var showDatePicker by rememberSaveable { mutableStateOf(false) }
                var selectedDateMillis by rememberSaveable { mutableStateOf<Long?>(null) }
                var showConfirmation by rememberSaveable { mutableStateOf(false) }
                var showBottomSheet by rememberSaveable { mutableStateOf(false) }
                var validationText by rememberSaveable { mutableStateOf("") }

                Scaffold(
                    snackbarHost = { UIKitSnackbarHost(hostState = snackbarHostState) }
                ) { contentPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(contentPadding)
                            .testTag("component-playground-list"),
                        contentPadding = PaddingValues(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = "ComposeUIKit",
                                    style = MaterialTheme.typography.headlineLarge
                                )
                                Text(
                                    text = "A practical component playground",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "Buttons · Inputs · Selection · Feedback",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        item {
                            DemoSection(title = "Buttons") {
                                UIKitButton(
                                    text = "Primary action",
                                    onClick = {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Primary action clicked")
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                UIKitButton(
                                    text = "Outlined action",
                                    onClick = { showConfirmation = true },
                                    variant = UIKitButtonVariant.Outlined,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                UIKitIconButton(
                                    onClick = { coroutineScope.launch { snackbarHostState.showSnackbar("Information action clicked") } },
                                    contentDescription = "Show information"
                                ) { Text("ⓘ") }
                                UIKitButton(
                                    text = "Text action",
                                    onClick = {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Text action clicked")
                                        }
                                    },
                                    variant = UIKitButtonVariant.Text,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                UIKitButton(
                                    text = "Disabled action",
                                    onClick = {},
                                    enabled = false,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                UIKitButton(
                                    text = "Saving changes",
                                    onClick = {},
                                    loading = true,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        item {
                            DemoSection(title = "Text inputs") {
                                UIKitTextField(
                                    value = name,
                                    onValueChange = { name = it },
                                    label = "Full name",
                                    placeholder = "Enter your name",
                                    helperText = "This is a state-hoisted input.",
                                    modifier = Modifier.fillMaxWidth().testTag("sample-name-field")
                                )
                                UIKitTextField(
                                    value = validationText,
                                    onValueChange = { validationText = it },
                                    label = "Error example",
                                    errorMessage = if (validationText == "invalid") "This value is not accepted" else null,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                UIKitPasswordField(
                                    value = password,
                                    onValueChange = { password = it },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                UIKitSearchField(
                                    query = query,
                                    onQueryChange = { query = it },
                                    placeholder = "Search components",
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        item {
                            DemoSection(title = "Selection controls") {
                                UIKitDropdownField(
                                    selectedOption = category,
                                    options = listOf("Design", "Development", "Testing"),
                                    onOptionSelected = { category = it },
                                    optionLabel = { it },
                                    label = "Category",
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text("Filters", style = MaterialTheme.typography.titleSmall)
                                    androidx.compose.foundation.layout.Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        listOf("All", "Active", "Archived").forEach { filter ->
                                            UIKitFilterChip(
                                                label = filter,
                                                selected = selectedFilter == filter,
                                                onClick = { selectedFilter = filter }
                                            )
                                        }
                                    }
                                }
                                UIKitCheckbox(
                                    checked = notificationsEnabled,
                                    onCheckedChange = { notificationsEnabled = it },
                                    label = "Enable notifications"
                                )
                                Text("Preferred contact", style = MaterialTheme.typography.titleSmall)
                                UIKitRadioGroup(
                                    options = listOf("Email", "Phone", "In-app"),
                                    selectedOption = deliveryMethod,
                                    onOptionSelected = { deliveryMethod = it },
                                    optionLabel = { it }
                                )
                                UIKitButton(
                                    text = selectedDateMillis?.let {
                                        "Date: ${DateFormat.getDateInstance().format(Date(it))}"
                                    } ?: "Choose date",
                                    onClick = { showDatePicker = true },
                                    variant = UIKitButtonVariant.Outlined
                                )
                            }
                        }

                        item {
                            DemoSection(title = "Feedback and layout") {
                                UIKitButton(
                                    text = "Open bottom sheet",
                                    onClick = { showBottomSheet = true },
                                    variant = UIKitButtonVariant.Outlined
                                )
                                UIKitEmptyState(
                                    title = "Nothing here yet",
                                    description = "An empty state can guide people towards their next action.",
                                    actionLabel = "Refresh",
                                    onAction = {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Refresh requested")
                                        }
                                    }
                                )
                            }
                        }

                        item {
                            Text(
                                text = "Built with Kotlin and Jetpack Compose Material 3",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                if (showDatePicker) {
                    UIKitDatePickerDialog(
                        onDateSelected = { selectedDateMillis = it },
                        onDismissRequest = { showDatePicker = false },
                        initialSelectedDateMillis = selectedDateMillis
                    )
                }

                if (showBottomSheet) {
                    UIKitBottomSheet(
                        onDismissRequest = { showBottomSheet = false }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text("Reusable bottom sheet", style = MaterialTheme.typography.titleLarge)
                            Text("The sheet content is supplied by the consuming application.")
                            UIKitButton(
                                text = "Close",
                                onClick = { showBottomSheet = false },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                if (showConfirmation) {
                    UIKitConfirmationDialog(
                        title = "Confirm action",
                        message = "This demonstrates a reusable confirmation dialog.",
                        onConfirm = {
                            showConfirmation = false
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Confirmed")
                            }
                        },
                        onDismiss = { showConfirmation = false }
                    )
                }
            }
        }
    }
}

@Composable
private fun DemoSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    UIKitCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                Text(title, style = MaterialTheme.typography.titleLarge)
                UIKitDivider()
                content(this)
            }
        )
    }
}
