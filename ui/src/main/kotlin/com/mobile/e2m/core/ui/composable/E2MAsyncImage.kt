package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import com.mobile.e2m.core.ui.R

@Composable
fun E2MAsyncImage(
    modifier: Modifier = Modifier,
    imageId: Int? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
) {
    imageId?.let {
        AsyncImage(
            modifier = modifier,
            model = ImageRequest.Builder(LocalContext.current)
                .data(it)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            contentScale = contentScale,
        )
    }
}

@Preview
@Composable
fun E2MAsyncImageGifPreview() {
    E2MAsyncImage(
        modifier = Modifier.fillMaxWidth(),
        imageId = R.raw.gif_logo_e2music,
    )
}

@Preview
@Composable
fun E2MAsyncImageSvgPreview() {
    E2MAsyncImage(
        modifier = Modifier.fillMaxSize(),
        imageId = R.raw.img_background_started,
        contentScale = ContentScale.Crop,
    )
}
