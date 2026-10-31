package dev.tauseef.composeuikit.components.buttons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/** Visual variants supported by [UIKitButton]. */
enum class UIKitButtonVariant {
    Primary,
    Outlined,
    Text
}

/**
 * A state-hoisted button with consistent ComposeUIKit defaults.
 *
 * @param text Accessible button label displayed to the user.
 * @param onClick Invoked when the enabled button is clicked.
 * @param modifier Modifier applied to the underlying Material button.
 * @param enabled Whether the button accepts user interaction.
 * @param loading Displays a progress indicator and prevents repeated clicks.
 * @param variant Visual style to render.
 * @param content Optional composable content displayed after the label.
 */
@Composable
fun UIKitButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    variant: UIKitButtonVariant = UIKitButtonVariant.Primary,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    val buttonEnabled = enabled && !loading

    val buttonContent: @Composable RowScope.() -> Unit = {
        Text(text = text)
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(16.dp)
                    .semantics { contentDescription = "Loading" },
                strokeWidth = 2.dp
            )
        } else {
            content?.invoke(this)
        }
    }

    when (variant) {
        UIKitButtonVariant.Primary -> Button(
            onClick = onClick,
            modifier = modifier,
            enabled = buttonEnabled,
            content = buttonContent
        )
        UIKitButtonVariant.Outlined -> OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            enabled = buttonEnabled,
            content = buttonContent
        )
        UIKitButtonVariant.Text -> TextButton(
            onClick = onClick,
            modifier = modifier,
            enabled = buttonEnabled,
            content = buttonContent
        )
    }
}

/**
 * Icon-only action button. Provide an accessible [contentDescription] for screen readers.
 */
@Composable
fun UIKitIconButton(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    androidx.compose.material3.IconButton(
        onClick = onClick,
        modifier = modifier.semantics {
            this.contentDescription = contentDescription
        },
        enabled = enabled,
        content = content
    )
}
