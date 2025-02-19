package com.smo.mobile.retail.core.ui.composable.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import com.mobile.e2m.core.ui.composable.snackBar.E2MSnackBarStatus
import com.mobile.e2m.core.ui.composable.snackBar.E2MSnackBarVisuals

suspend fun SnackbarHostState.showE2MSnackBar(
    message: String,
    status: E2MSnackBarStatus = E2MSnackBarStatus.Success,
    duration: SnackbarDuration = SnackbarDuration.Short,
): SnackbarResult = showSnackbar(
    E2MSnackBarVisuals(
        message = message,
        status = status,
        duration = duration,
    )
)
