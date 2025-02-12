package com.mobile.e2m.core.ui.composable.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.theme.Write100
import com.mobile.e2m.core.ui.theme.Write800

@Composable
fun E2MBackgroundLight() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Write100, Write800, Write100),
                    radius = 1250f,
                )
            )
            .blur(E2MTheme.alias.size.spacing.small)
    )
}

@Preview
@Composable
fun BackgroundLightPreview() {
    E2MBackgroundLight()
}
