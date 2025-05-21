package com.github.mcgalanes.happtech.core.domain.model

data class ArtObjectLight(
    val objectNumber: String,
    val title: String,
    val hasImage: Boolean,
    val webImage: Image?,
) {
    data class Image(
        val ratio: Float,
        val url: String,
    )
}
