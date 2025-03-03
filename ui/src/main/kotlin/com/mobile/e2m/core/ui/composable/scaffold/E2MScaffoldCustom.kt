@file:OptIn(ExperimentalLayoutApi::class)

package com.mobile.e2m.core.ui.composable.scaffold

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.MutableWindowInsets
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.onConsumedWindowInsetsChanged
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMapNotNull
import androidx.compose.ui.util.fastMaxBy

@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    val safeInsets = remember(contentWindowInsets) { MutableWindowInsets(contentWindowInsets) }
    Surface(
        modifier =
        modifier.onConsumedWindowInsetsChanged { consumedWindowInsets ->
            safeInsets.insets = contentWindowInsets.exclude(consumedWindowInsets)
        },
        color = containerColor,
        contentColor = contentColor
    ) {
        ScaffoldLayout(
            fabPosition = floatingActionButtonPosition,
            topBar = topBar,
            bottomBar = bottomBar,
            content = content,
            snackbar = snackbarHost,
            contentWindowInsets = safeInsets,
            fab = floatingActionButton
        )
    }
}

@Composable
private fun ScaffoldLayout(
    fabPosition: FabPosition,
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    snackbar: @Composable () -> Unit,
    fab: @Composable () -> Unit,
    contentWindowInsets: WindowInsets,
    bottomBar: @Composable () -> Unit
) {
    SubcomposeLayout { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight

        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        val topBarPlaceables =
            subcompose(ScaffoldLayoutContent.TopBar, topBar).fastMap {
                it.measure(looseConstraints)
            }

        val topBarHeight = topBarPlaceables.fastMaxBy { it.height }?.height ?: 0

        val snackbarPlaceables =
            subcompose(ScaffoldLayoutContent.Snackbar, snackbar).fastMap {
                val leftInset = contentWindowInsets.getLeft(this@SubcomposeLayout, layoutDirection)
                val rightInset =
                    contentWindowInsets.getRight(this@SubcomposeLayout, layoutDirection)
                val bottomInset = contentWindowInsets.getBottom(this@SubcomposeLayout)
                it.measure(looseConstraints.offset(-leftInset - rightInset, -bottomInset))
            }

        val snackbarHeight = snackbarPlaceables.fastMaxBy { it.height }?.height ?: 0
        val snackbarWidth = snackbarPlaceables.fastMaxBy { it.width }?.width ?: 0

        val fabPlaceables =
            subcompose(ScaffoldLayoutContent.Fab, fab).fastMapNotNull { measurable ->
                val leftInset = contentWindowInsets.getLeft(this@SubcomposeLayout, layoutDirection)
                val rightInset =
                    contentWindowInsets.getRight(this@SubcomposeLayout, layoutDirection)
                val bottomInset = contentWindowInsets.getBottom(this@SubcomposeLayout)
                measurable
                    .measure(looseConstraints.offset(-leftInset - rightInset, -bottomInset))
                    .takeIf { it.height != 0 && it.width != 0 }
            }

        val fabPlacement =
            if (fabPlaceables.isNotEmpty()) {
                val fabWidth = fabPlaceables.fastMaxBy { it.width }!!.width
                val fabHeight = fabPlaceables.fastMaxBy { it.height }!!.height
                val fabLeftOffset =
                    when (fabPosition) {
                        FabPosition.Bottom,
                        FabPosition.End,
                        FabPosition.EndOverlay -> {
                            if (layoutDirection == LayoutDirection.Ltr) {
                                layoutWidth - fabWidth
                            } else {
                                FabSpacing.toPx().toInt()
                            }
                        }

                        else -> {
                            if (layoutDirection == LayoutDirection.Ltr) {
                                layoutWidth - fabWidth
                            } else {
                                FabSpacing.toPx().toInt()
                            }
                        }
                    }

                FabPlacement(left = fabLeftOffset, width = fabWidth, height = fabHeight)
            } else {
                null
            }

        val bottomBarPlaceables =
            subcompose(ScaffoldLayoutContent.BottomBar) { bottomBar() }
                .fastMap { it.measure(looseConstraints) }

        val bottomBarHeight = bottomBarPlaceables.fastMaxBy { it.height }?.height
        val fabOffsetFromBottom =
            fabPlacement?.let {
                if (bottomBarHeight == null || fabPosition == FabPosition.EndOverlay) {
                    it.height + contentWindowInsets.getBottom(this@SubcomposeLayout)
                } else if (fabPosition == FabPosition.Center) {
                    bottomBarHeight + it.height
                } else {
                    bottomBarHeight + it.height + FabSpacing.roundToPx()
                }
            }

        val snackbarOffsetFromBottom =
            if (snackbarHeight != 0) {
                snackbarHeight +
                        (fabOffsetFromBottom
                            ?: bottomBarHeight
                            ?: contentWindowInsets.getBottom(this@SubcomposeLayout))
            } else {
                0
            }

        val bodyContentPlaceables =
            subcompose(ScaffoldLayoutContent.MainContent) {
                val insets = contentWindowInsets.asPaddingValues(this@SubcomposeLayout)
                val innerPadding =
                    PaddingValues(
                        top =
                        if (topBarPlaceables.isEmpty()) {
                            insets.calculateTopPadding()
                        } else {
                            topBarHeight.toDp()
                        },
                        bottom =
                        if (bottomBarPlaceables.isEmpty() || bottomBarHeight == null) {
                            insets.calculateBottomPadding()
                        } else {
                            bottomBarHeight.toDp()
                        },
                        start =
                        insets.calculateStartPadding(
                            (this@SubcomposeLayout).layoutDirection
                        ),
                        end =
                        insets.calculateEndPadding((this@SubcomposeLayout).layoutDirection)
                    )
                content(innerPadding)
            }
                .fastMap { it.measure(looseConstraints) }

        layout(layoutWidth, layoutHeight) {
            bodyContentPlaceables.fastForEach { it.place(0, 0) }
            topBarPlaceables.fastForEach { it.place(0, 0) }
            snackbarPlaceables.fastForEach {
                it.place(
                    (layoutWidth - snackbarWidth) / 2 +
                            contentWindowInsets.getLeft(this@SubcomposeLayout, layoutDirection),
                    layoutHeight - snackbarOffsetFromBottom
                )
            }
            bottomBarPlaceables.fastForEach { it.place(0, layoutHeight - (bottomBarHeight ?: 0)) }
            fabPlacement?.let { placement ->
                fabPlaceables.fastForEach {
                    it.place(placement.left, layoutHeight - fabOffsetFromBottom!!)
                }
            }
        }
    }
}

object ScaffoldDefaults {
    val contentWindowInsets: WindowInsets
        @Composable get() = WindowInsets.systemBars
}

@JvmInline
value class FabPosition internal constructor(@Suppress("unused") private val value: Int) {
    companion object {
        val Bottom = FabPosition(0)

        val Center = FabPosition(1)

        val End = FabPosition(2)

        val EndOverlay = FabPosition(3)
    }

    override fun toString(): String {
        return when (this) {
            Bottom -> "FabPosition.Start"
            Center -> "FabPosition.Center"
            End -> "FabPosition.End"
            else -> "FabPosition.EndOverlay"
        }
    }
}

@Immutable
internal class FabPlacement(val left: Int, val width: Int, val height: Int)

private val FabSpacing = 80.dp

private enum class ScaffoldLayoutContent {
    TopBar,
    MainContent,
    Snackbar,
    Fab,
    BottomBar
}
