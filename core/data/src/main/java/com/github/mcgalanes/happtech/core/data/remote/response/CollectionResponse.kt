package com.github.mcgalanes.happtech.core.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionResponse(
    @SerialName("artObjects") val artObjects: List<ArtObject>?,
    @SerialName("count") val count: Int?,
    @SerialName("elapsedMilliseconds") val elapsedMilliseconds: Int?,
) {
    @Serializable
    data class ArtObject(
        @SerialName("id") val id: String?,
        @SerialName("links") val links: Links?,
        @SerialName("objectNumber") val objectNumber: String?,
        @SerialName("title") val title: String?,
        @SerialName("hasImage") val hasImage: Boolean?,
        @SerialName("principalOrFirstMaker") val principalOrFirstMaker: String?,
        @SerialName("longTitle") val longTitle: String?,
        @SerialName("showImage") val showImage: Boolean?,
        @SerialName("permitDownload") val permitDownload: Boolean?,
        @SerialName("webImage") val webImage: Image?,
        @SerialName("headerImage") val headerImage: Image?,
        @SerialName("productionPlaces") val productionPlaces: List<String>?,
    )

    @Serializable
    data class Links(
        @SerialName("self") val self: String?,
        @SerialName("web") val web: String?,
    )

    @Serializable
    data class Image(
        @SerialName("guid") val guid: String?,
        @SerialName("offsetPercentageX") val offsetPercentageX: Int?,
        @SerialName("offsetPercentageY") val offsetPercentageY: Int?,
        @SerialName("width") val width: Int?,
        @SerialName("height") val height: Int?,
        @SerialName("url") val url: String?,
    )
}
