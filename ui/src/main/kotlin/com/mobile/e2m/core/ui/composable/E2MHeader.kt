package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
fun E2MHeader(
    modifier: Modifier = Modifier,
    iconId: Int? = null,
    title: String? = null,
    leadingIconOnClick: () -> Unit = { },
) {
    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = size.spacing.small)
            .padding(top = topPadding + size.spacing.small)
    ) {
        iconId?.let {
            Icon(
                modifier = Modifier
                    .size(size.icon.smallX)
                    .align(Alignment.CenterStart)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { leadingIconOnClick() },
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = color.icon.white
            )
        }

        title?.let {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = it,
                style = E2MTheme.typography.title.black,
                color = color.text.white,
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
