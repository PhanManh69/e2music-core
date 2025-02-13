package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.theme.E2MTheme

@Immutable
data class E2MSongsData(
    val imageId: Int? = null,
    val name: String? = null,
    val singer: String? = null,
)

@Composable
fun E2MSongsItem(
    modifier: Modifier = Modifier,
    iconId: Int? = null,
    shape: Shape = CircleShape,
    blur: Dp = 0.dp,
    songItem: E2MSongsData,
    imageOnClick: () -> Unit = { },
    iconOnClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small2x),
    ) {
        Box(
            modifier = Modifier.debounceClickable { imageOnClick() },
            contentAlignment = Alignment.Center,
        ) {
            songItem.imageId?.let {
                E2MImage(
                    modifier = Modifier
                        .size(size.icon.large)
                        .clip(shape)
                        .border(
                            width = size.stroke.thick,
                            color = color.border.blur2Light,
                            shape = shape,
                        )
                        .blur(
                            radius = blur,
                            edgeTreatment = BlurredEdgeTreatment(shape)
                        ),
                    imageId = it,
                    contentDescription = songItem.name,
                )
            }

            if (blur != 0.dp) {
                E2MIcon(
                    modifier = Modifier.size(size.spacing.large),
                    iconId = R.drawable.ic_play_circle,
                    tint = color.surface.blur2Light,
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(size.spacing.small4x),
        ) {
            songItem.name?.let {
                Text(
                    text = it,
                    style = style.base.regular,
                    color = color.text.white200,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            songItem.singer?.let {
                Text(
                    text = it,
                    style = style.small.regular,
                    color = color.text.white600,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        iconId?.let {
            E2MIcon(
                modifier = Modifier.size(size.icon.smallX),
                iconId = it,
                tint = color.icon.blue2Light,
                onClick = { iconOnClick() }
            )
        }
    }
}

@Preview
@Composable
fun E2MSingsItemPreview() {
    val songItem = E2MSongsData(
        imageId = R.drawable.img_song,
        name = "Tên bài hát",
        singer = "Tên nghệ sĩ",
    )

    E2MSongsItem(
        iconId = R.drawable.ic_play_circle,
        songItem = songItem,
    )
}
