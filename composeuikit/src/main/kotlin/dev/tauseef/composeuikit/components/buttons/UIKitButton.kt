package dev.tauseef.composeuikit.components.buttons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
 * @param variant Visual style to render.
 * @param content Optional trailing or leading content, such as an icon.
 */
@Composable
fun UIKitButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: UIKitButtonVariant = UIKitButtonVariant.Primary,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    when (variant) {
        UIKitButtonVariant.Primary -> Button(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            content = {
                Text(text = text)
                content?.invoke(this)
            }
        )
        UIKitButtonVariant.Outlined -> OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            content = {
                Text(text = text)
                content?.invoke(this)
            }
        )
        UIKitButtonVariant.Text -> TextButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            content = {
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.primary
                )
                content?.invoke(this)
            }
        )
    }
}
