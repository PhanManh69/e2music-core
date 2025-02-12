package com.mobile.e2m.core.ui.composable.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.e2m.core.ui.theme.Black100
import com.mobile.e2m.core.ui.theme.Black400
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
fun E2MBackgroundDark() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Black100, Black400, Black100),
                    radius = 1250f
                )
            )
            .blur(E2MTheme.alias.size.spacing.small)
    )
}

@Preview
@Composable
fun BackgroundDarkPreview() {
    E2MBackgroundDark()
}
