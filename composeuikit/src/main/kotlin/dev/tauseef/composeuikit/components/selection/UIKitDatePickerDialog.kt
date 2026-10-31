package dev.tauseef.composeuikit.components.selection

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable

/**
 * Material 3 date picker dialog.
 *
 * The selected timestamp is returned in UTC milliseconds, matching
 * [androidx.compose.material3.DatePickerState.selectedDateMillis].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIKitDatePickerDialog(
    onDateSelected: (Long) -> Unit,
    onDismissRequest: () -> Unit,
    initialSelectedDateMillis: Long? = null,
    title: String = "Select date",
    confirmText: String = "Confirm",
    dismissText: String = "Cancel"
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDateMillis
    )

    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let(onDateSelected)
                    onDismissRequest()
                },
                enabled = datePickerState.selectedDateMillis != null
            ) {
                Text(confirmText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(dismissText)
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            title = { Text(title) }
        )
    }
}
