package com.github.mcgalanes.happtech.feature.museumcollection.detail.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.github.mcgalanes.happtech.core.design.component.NoImage
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail

@Composable
fun ImageCard(
    image: ArtObjectDetail.Image?,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Surface(
        modifier = modifier.aspectRatio(image?.ratio ?: 1f),
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.surfaceDim,
        shadowElevation = 4.dp,
    ) {
        if (image?.url?.isNotBlank() == true) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(image.url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            NoImage(modifier = Modifier.fillMaxSize())
        }
    }
}
