package com.github.mcgalanes.happtech.feature.museumcollection.list.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.github.mcgalanes.happtech.core.design.HapptechTheme
import com.github.mcgalanes.happtech.core.design.component.NoImage
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight

@Composable
fun ArtObjectItem(
    artObject: ArtObjectLight,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (pressed) .96f else 1f,
        animationSpec = tween(durationMillis = 150),
        label = "item-pressed-scale",
    )

    Surface(
        modifier = modifier
            .aspectRatio(artObject.webImage?.ratio ?: 1f)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceDim,
    ) {
        if (artObject.hasImage) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(artObject.webImage?.url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = artObject.title,
            )
        } else {
            NoImage(
                modifier = Modifier.fillMaxSize(),
                contentDescription = artObject.title,
            )
        }
    }
}

@Preview
@Composable
private fun Preview_ArtObjectItem() {
    HapptechTheme {
        ArtObjectItem(
            artObject = ArtObjectLight(
                objectNumber = "1",
                title = "Title",
                hasImage = true,
                webImage = ArtObjectLight.Image(
                    ratio = 16 / 9f,
                    url = "https://lh5.ggpht.com/EHhJDrv4IB_89m9w9VlcYRYHYOuvU72iwD11oZ1HL3J5QcCMfmAD48CVxAtUwts9RT55W4lWSPI19wb1lSRZ9zecKMA=s0",
                ),
            ),
            onClick = {},
        )
    }
}
