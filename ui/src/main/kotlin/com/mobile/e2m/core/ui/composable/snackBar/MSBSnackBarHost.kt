package com.mobile.e2m.core.ui.composable.snackBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun E2MSnackBarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom),
    ) { data ->
        val visuals = remember { data.visuals }

        if (visuals is E2MSnackBarVisuals) {
            E2MSnackBar(snackBarData = data)
        } else {
            Snackbar(data)
        }
    }
}
