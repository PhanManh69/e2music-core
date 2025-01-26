package com.mobile.e2m.core.ui.theme.global

import androidx.compose.runtime.compositionLocalOf

data class E2MGlobal(
    val color: E2MGlobalColor,
    val size: E2MGlobalSize,
) {
    companion object {
        fun default() = E2MGlobal(
            color = E2MGlobalColor.default(),
            size = E2MGlobalSize.default(),
        )
    }
}

val LocalE2MGlobal = compositionLocalOf {
    E2MGlobal.default()
}
