package com.github.mcgalanes.happtech.feature.museumcollection.detail.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandCircleDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mcgalanes.happtech.core.design.HapptechTheme
import com.github.mcgalanes.happtech.core.design.component.VerticalSpacer
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail

@Composable
fun SummaryCard(
    artObject: ArtObjectDetail,
    expanded: Boolean,
    onHeaderClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        Column(
            modifier = Modifier
                .clickable(
                    onClick = onHeaderClick,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                )
                .padding(
                    vertical = 12.dp,
                    horizontal = 20.dp,
                ),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = artObject.title,
                    style = MaterialTheme.typography.titleLarge,
                )

                val rotationAnim by animateFloatAsState(
                    targetValue = if (expanded) 0f else 180f,
                    label = "expand-icon-rotation-anim",
                )

                Icon(
                    modifier = Modifier.rotate(rotationAnim),
                    imageVector = Icons.Default.ExpandCircleDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f),
                )
            }

            VerticalSpacer(4.dp)

            artObject.principalMakers.forEach {
                MakerItem(it)
            }

            VerticalSpacer(16.dp)

            Text(
                text = artObject.description,
                style = MaterialTheme.typography.bodyMedium,
            )

            AnimatedVisibility(expanded) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    VerticalSpacer(24.dp)

                    HorizontalDivider(modifier = Modifier.fillMaxWidth())

                    VerticalSpacer(16.dp)

                    Text(
                        text = artObject.objectCollection.joinToString(separator = "\n\n"),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview_SummaryCard() {
    var expanded by remember { mutableStateOf(false) }

    HapptechTheme {
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxSize(),
        ) {
            SummaryCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                artObject = ArtObjectDetail(
                    objectNumber = "FAKE-1234",
                    title = "Fake Art Object",
                    description = "This is a fake art object for testing purposes.",
                    objectCollection = listOf("Fake Collection"),
                    techniques = listOf("Fake Technique"),
                    principalMakers = listOf(
                        ArtObjectDetail.PrincipalMaker(
                            name = "John Doe",
                            dateOfBirth = "1900",
                            dateOfDeath = "2000",
                        ),
                        ArtObjectDetail.PrincipalMaker(
                            name = "Sonny Moore",
                            dateOfBirth = "1950",
                            dateOfDeath = "1978",
                        ),
                    ),
                    presentingDate = "2023-10-01",
                    documentation = listOf("Fake Document 1", "Fake Document 2"),
                    dimensions = listOf(
                        ArtObjectDetail.Dimension(
                            unit = "cm",
                            type = "height",
                            value = "300",
                        ),
                        ArtObjectDetail.Dimension(
                            unit = "cm",
                            type = "width",
                            value = "200",
                        ),
                        ArtObjectDetail.Dimension(
                            unit = "cm",
                            type = "depth",
                            value = "100",
                        ),
                    ),
                    image = ArtObjectDetail.Image(
                        ratio = 1.5f,
                        url = "https://example.com/fake-image.jpg",
                    ),
                ),
                expanded = expanded,
                onHeaderClick = { expanded = !expanded },
            )
        }
    }
}
