package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    imageUrl: String? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
) {
    var isImageLoaded by remember { mutableStateOf(false) }

    if (imageUrl != null) {
        AsyncImage(
            modifier = modifier.then(if (!isImageLoaded) Modifier.shimmerEffect() else Modifier),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .listener(
                    onSuccess = { _, _ -> isImageLoaded = true },
                    onError = { _, _ -> isImageLoaded = true }
                )
                .build(),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
        )
    } else if (imageId != null) {
        AsyncImage(
            modifier = modifier,
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageId)
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

@Preview
@Composable
fun E2MAsyncImageUrlPreview() {
    E2MAsyncImage(
        modifier = Modifier.fillMaxSize(),
        imageUrl = "https://lh3.googleusercontent.com/9fE20eMGF4KeEmzNLgba9buDFfTIs68bj1l4U8F-jIKiSv-QoSOMlobhHfy0-puH8ly4-XyAyK1iI5s=w544-h544-l90-rj",
        contentScale = ContentScale.Crop,
    )
}
