package com.github.mcgalanes.happtech.core.domain.model

data class ArtObject(
    val id: String,
    val objectNumber: String,
    val title: String,
    val hasImage: Boolean,
    val principalOrFirstMaker: String,
    val longTitle: String,
    val webImage: Image?,
    val headerImage: Image?,
    val productionPlaces: Set<String>,
) {
    data class Image(
        val ratio: Float,
        val url: String,
    )
}
