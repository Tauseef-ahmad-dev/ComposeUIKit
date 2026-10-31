package dev.tauseef.composeuikit.components.sheets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Modal Material 3 bottom sheet with caller-controlled visibility.
 *
 * The sheet state is intentionally kept inside the component so consumers do not
 * need to depend on Material 3's experimental sheet-state API.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIKitBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = true,
    content: @Composable () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        content = { content() }
    )
}
