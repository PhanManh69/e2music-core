package com.mobile.e2m.core.ui.composable.snackBar

import androidx.annotation.Keep
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Stable

@Stable
@Keep
data class E2MSnackBarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    override val withDismissAction: Boolean = true,
    val status: E2MSnackBarStatus = E2MSnackBarStatus.Success,
) : SnackbarVisuals

@Keep
sealed interface E2MSnackBarStatus {
    @Keep
    data object Success : E2MSnackBarStatus

    @Keep
    data object Error : E2MSnackBarStatus
}
