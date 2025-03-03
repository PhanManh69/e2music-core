package com.mobile.e2m.core.ui.composable.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun E2MScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = { },
    utilityBar: @Composable () -> Unit = { },
    overlay: @Composable () -> Unit = { },
    bottomBar: @Composable () -> Unit = { },
    snackBarHost: @Composable () -> Unit = { },
    floatingActionButton: @Composable () -> Unit = { },
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Color.Transparent,
    contentColor: Color = Color.Transparent,
    contentWindowInsets: WindowInsets = ScaffoldDefaults
        .contentWindowInsets
        .union(WindowInsets.displayCutout)
        .only(WindowInsetsSides.Horizontal),
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackBarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = WindowInsets(0.dp),
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
                utilityBar()
                Box(
                    modifier = Modifier
                        .windowInsetsPadding(insets = contentWindowInsets),
                ) {
                    content()
                }
            }
            Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
                overlay()
            }
        },
    )
}
