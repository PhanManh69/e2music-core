package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
fun E2MPlaylistItem(
    modifier: Modifier = Modifier,
    imageId: Int? = null,
    name: String? = null,
    numberSongs: Int? = null,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Box(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .height(size.spacing.large5x)
                .width(size.spacing.large6x - size.spacing.smallX)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(size.radius.radius2))
                .background(
                    color = color.surface.button,
                    shape = RoundedCornerShape(size.radius.radius2),
                )

        )

        imageId?.let {
            E2MImage(
                modifier = Modifier
                    .height(size.spacing.large5x - size.spacing.small4x)
                    .width(size.spacing.large6x)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(size.radius.radius2))
                    .border(
                        width = size.stroke.thick,
                        color = color.border.blur2Light,
                        shape = RoundedCornerShape(size.radius.radius2),
                    ),
                imageId = it,
                contentDescription = name,
            )
        }

        Box(
            modifier = Modifier
                .padding(size.spacing.small2x)
                .align(Alignment.BottomEnd)
                .background(
                    color = color.surface.black.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(size.radius.radius2),
                )
                .clip(RoundedCornerShape(size.radius.radius2)),
        ) {
            Row(
                modifier = Modifier.padding(
                    vertical = size.spacing.small4x,
                    horizontal = size.spacing.small2x,
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(size.spacing.small4x),
            ) {
                E2MIcon(
                    modifier = Modifier.size(size.icon.small3X),
                    iconId = R.drawable.ic_list_music,
                )

                numberSongs?.let {
                    Text(
                        text = it.toString(),
                        style = style.small.regular,
                        color = color.text.white,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
