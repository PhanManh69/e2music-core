package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.e2m.core.ui.R

@Composable
fun E2MImage(
    modifier: Modifier = Modifier,
    imageId: Int? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    imageId?.let {
        Image(
            modifier = modifier,
            painter = painterResource(id = it),
            contentDescription = contentDescription,
            contentScale = contentScale,
        )
    }
}

@Preview
@Composable
fun E2MImagePreview() {
    E2MImage(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(8.dp),
            ),
        imageId = R.drawable.img_song,
        contentDescription = "Image song"
    )
}
