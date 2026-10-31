package dev.tauseef.composeuikit.components.feedback

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/** ComposeUIKit-styled host for a caller-owned SnackbarHostState. */
@Composable
fun UIKitSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
        snackbar = { snackbarData ->
            Snackbar(snackbarData = snackbarData)
        }
    )
}
