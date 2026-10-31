package dev.tauseef.composeuikit.components.layout

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Horizontal Material 3 divider. */
@Composable
fun UIKitDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = androidx.compose.material3.MaterialTheme.colorScheme.outlineVariant
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}
