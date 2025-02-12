package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
fun E2MHeader(
    modifier: Modifier = Modifier,
    leadingIconId: Int? = null,
    trailingIconId: Int? = null,
    title: String? = null,
    topPadding: Dp = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
    leadingIconOnClick: () -> Unit = { },
    trailingIconOnClick: () -> Unit = { },
) {
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = size.spacing.small)
            .padding(top = topPadding + size.spacing.small2x)
    ) {
        leadingIconId?.let {
            E2MIcon(
                modifier = Modifier
                    .size(size.icon.smallX)
                    .align(Alignment.CenterStart),
                iconId = it,
                onClick = { leadingIconOnClick() }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            contentAlignment = Alignment.Center,
        ) {
            title?.let {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = it,
                    style = E2MTheme.typography.title.black,
                    color = color.text.white,
                )
            }
        }

        trailingIconId?.let {
            E2MIcon(
                modifier = Modifier
                    .size(size.icon.smallX)
                    .align(Alignment.CenterEnd),
                iconId = it,
                onClick = { trailingIconOnClick() },
            )
        }
    }
}

@Preview
@Composable
fun E2MHeaderPreview() {
    E2MHeader(
        title = "Header"
    )
}
