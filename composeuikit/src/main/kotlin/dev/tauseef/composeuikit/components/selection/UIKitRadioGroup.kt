package dev.tauseef.composeuikit.components.selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

/** Vertical radio group for a generic set of caller-owned options. */
@Composable
fun <T> UIKitRadioGroup(
    options: List<T>,
    selectedOption: T?,
    onOptionSelected: (T) -> Unit,
    optionLabel: (T) -> String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column(modifier = modifier) {
        options.forEach { option ->
            val selected = option == selectedOption
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = selected,
                        enabled = enabled,
                        role = Role.RadioButton,
                        onClick = { onOptionSelected(option) }
                    )
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected,
                    onClick = null,
                    enabled = enabled
                )
                Text(
                    text = optionLabel(option),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
