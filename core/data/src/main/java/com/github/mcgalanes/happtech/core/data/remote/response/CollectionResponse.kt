package com.github.mcgalanes.happtech.core.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.github.mcgalanes.happtech.core.domain.model.ArtObject as ArtObjectDomain

@Serializable
data class CollectionResponse(
    @SerialName("artObjects") val artObjects: List<ArtObject>?,
    @SerialName("count") val count: Int?,
) {
    @Serializable
    data class ArtObject(
        @SerialName("id") val id: String?,
        @SerialName("objectNumber") val objectNumber: String?,
        @SerialName("title") val title: String?,
        @SerialName("hasImage") val hasImage: Boolean?,
        @SerialName("principalOrFirstMaker") val principalOrFirstMaker: String?,
        @SerialName("longTitle") val longTitle: String?,
        @SerialName("showImage") val showImage: Boolean?,
        @SerialName("webImage") val webImage: Image?,
        @SerialName("headerImage") val headerImage: Image?,
        @SerialName("productionPlaces") val productionPlaces: List<String>?,
    ) {
        @Serializable
        data class Image(
            @SerialName("guid") val guid: String?,
            @SerialName("width") val width: Int?,
            @SerialName("height") val height: Int?,
            @SerialName("url") val url: String?,
        )
    }
}

fun CollectionResponse.toDomain(): List<ArtObjectDomain> {
    return requireNotNull(artObjects) { "ArtObjects cannot be null" }.map { it.toDomain() }
}

//TODO: tests
fun CollectionResponse.ArtObject.toDomain(): ArtObjectDomain {
    return ArtObjectDomain(
        id = id.orEmpty(),
        objectNumber = objectNumber.orEmpty(),
        title = title.orEmpty(),
        hasImage = webImage?.guid != null && hasImage ?: false,
        principalOrFirstMaker = principalOrFirstMaker.orEmpty(),
        longTitle = longTitle.orEmpty(),
        webImage = webImage?.toDomain(),
        headerImage = headerImage?.toDomain(),
        productionPlaces = productionPlaces?.toSet() ?: emptySet(),
    )
}

//TODO: tests
fun CollectionResponse.ArtObject.Image.toDomain(): ArtObjectDomain.Image? {
    if (guid == null) return null

    val widthFloat = requireNotNull(width).toFloat()
    val heightFloat = requireNotNull(height).toFloat()

    return ArtObjectDomain.Image(
        ratio = widthFloat / heightFloat,
        url = url.orEmpty(),
    )
}
