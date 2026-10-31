package dev.tauseef.composeuikit.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Flexible Material 3 alert dialog wrapper.
 *
 * Use [UIKitConfirmationDialog] for the common confirm/cancel interaction.
 */
@Composable
fun UIKitDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    message: String? = null,
    dismissButton: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        icon = icon,
        title = title?.let { { Text(it) } },
        text = message?.let { { Text(it) } },
        confirmButton = confirmButton,
        dismissButton = dismissButton
    )
}

/** Standard confirmation dialog with explicit confirm and dismiss callbacks. */
@Composable
fun UIKitConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    confirmText: String = "Confirm",
    dismissText: String = "Cancel"
) {
    UIKitDialog(
        onDismissRequest = onDismiss,
        title = title,
        message = message,
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onConfirm) {
                Text(confirmText)
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text(dismissText)
            }
        }
    )
}
