package dev.tauseef.composeuikit.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tauseef.composeuikit.components.buttons.UIKitButton
import dev.tauseef.composeuikit.components.inputs.UIKitTextField
import dev.tauseef.composeuikit.components.selection.UIKitCheckbox
import dev.tauseef.composeuikit.theme.UIKitTheme

@Preview(
    name = "ComposeUIKit · Light",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ComposeUIKitLightPreview() {
    ComponentPreviewContent()
}

@Preview(
    name = "ComposeUIKit · Dark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ComposeUIKitDarkPreview() {
    ComponentPreviewContent(darkTheme = true)
}

@Composable
private fun ComponentPreviewContent(darkTheme: Boolean = false) {
    UIKitTheme(darkTheme = darkTheme) {
        val name = remember { mutableStateOf("Tauseef Ahmad") }
        val notifications = remember { mutableStateOf(true) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("ComposeUIKit")
            UIKitTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = "Full name",
                helperText = "Preview of a controlled input",
                modifier = Modifier.fillMaxWidth()
            )
            UIKitCheckbox(
                checked = notifications.value,
                onCheckedChange = { notifications.value = it },
                label = "Enable notifications"
            )
            UIKitButton(
                text = "Save changes",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
