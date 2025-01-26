package com.mobile.e2m.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.mobile.e2m.core.ui.theme.alias.E2MAlias
import com.mobile.e2m.core.ui.theme.alias.LocalE2MAlias
import com.mobile.e2m.core.ui.theme.global.E2MGlobal
import com.mobile.e2m.core.ui.theme.global.LocalE2MGlobal

object E2MTheme {
    val typography
        @Composable @ReadOnlyComposable get() = LocalE2MTypography.current

    val alias
        @Composable @ReadOnlyComposable get() = LocalE2MAlias.current

    val global
        @Composable @ReadOnlyComposable get() = LocalE2MGlobal.current
}

@Composable
fun E2MTheme(
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    shapes: Shapes = MaterialTheme.shapes,
    typography: E2MTypography = E2MTheme.typography,
    alias: E2MAlias = E2MTheme.alias,
    global: E2MGlobal = E2MTheme.global,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
    ) {
        CompositionLocalProvider(
            LocalE2MTypography provides typography,
            LocalE2MAlias provides alias,
            LocalE2MGlobal provides global,
            content = content,
        )
    }
}
