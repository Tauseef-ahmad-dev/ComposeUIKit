package dev.tauseef.composeuikit.components.inputs

import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

/** Search field with a caller-controlled query and an accessible clear action. */
@Composable
fun UIKitSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
    enabled: Boolean = true
) {
    UIKitTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        placeholder = placeholder,
        enabled = enabled,
        singleLine = true,
        leadingIcon = { Text("⌕") },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = { onQueryChange("") },
                    enabled = enabled,
                    modifier = Modifier.semantics {
                        contentDescription = "Clear search"
                    }
                ) {
                    Text("×")
                }
            }
        }
    )
}
