package com.github.mcgalanes.happtech.feature.museumcollection.detail.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail.PrincipalMaker

@Composable
fun MakerItem(
    maker: PrincipalMaker,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = "${maker.name}\u00A0•\u00A0${maker.dateOfBirth ?: ""}—${maker.dateOfDeath ?: ""}",
        style = MaterialTheme.typography.labelMedium,
    )
}
