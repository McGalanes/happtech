package com.github.mcgalanes.happtech.core.domain.model

data class ArtObjectDetail(
    val objectNumber: String,
    val title: String,
    val description: String,
    val objectCollection: List<String>,
    val techniques: List<String>,
    val principalMakers: List<PrincipalMaker>,
    val presentingDate: String?,
    val documentation: List<String>,
    val dimensions: List<Dimension>,
    val image: Image?,
) {
    data class PrincipalMaker(
        val name: String,
        val dateOfBirth: String?,
        val dateOfDeath: String?,
    )

    data class Dimension(
        val unit: String,
        val type: String,
        val value: String,
    )

    data class Image(
        val ratio: Float,
        val url: String,
    )
}
