package com.github.mcgalanes.happtech.core.database.mapper

import com.github.mcgalanes.happtech.core.database.entity.ArtObjectLightEntity
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight

fun ArtObjectLightEntity.toDomain(): ArtObjectLight {
    return ArtObjectLight(
        objectNumber = objectNumber,
        title = title,
        hasImage = hasImage,
        webImage = if (imageUrl != null && imageRatio != null) {
            ArtObjectLight.Image(
                url = imageUrl,
                ratio = imageRatio,
            )
        } else null,
    )
}

fun ArtObjectLight.toEntity(query: String): ArtObjectLightEntity {
    return ArtObjectLightEntity(
        objectNumber = objectNumber,
        title = title,
        hasImage = hasImage,
        imageUrl = webImage?.url,
        imageRatio = webImage?.ratio,
        query = query,
    )
} 
