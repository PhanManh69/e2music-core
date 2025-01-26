package com.mobile.e2m.core.ui.theme.alias

import androidx.compose.runtime.compositionLocalOf

data class E2MAlias(
    val color: E2MAliasColor,
    val size: E2MAliasSize,
) {
    companion object {
        fun default() = E2MAlias(
            color = E2MAliasColor.default(),
            size = E2MAliasSize.default(),
        )
    }
}

val LocalE2MAlias = compositionLocalOf {
    E2MAlias.default()
}
