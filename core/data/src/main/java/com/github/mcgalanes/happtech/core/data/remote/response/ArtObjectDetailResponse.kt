package com.github.mcgalanes.happtech.core.data.remote.response

import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtObjectDetailResponse(
    @SerialName("artObject") val artObject: ArtObject?,
) {
    @Serializable
    data class ArtObject(
        @SerialName("objectNumber") val objectNumber: String?,
        @SerialName("title") val title: String?,
        @SerialName("description") val description: String?,
        @SerialName("objectCollection") val objectCollection: List<String>?,
        @SerialName("techniques") val techniques: List<String>?,
        @SerialName("principalMakers") val principalMakers: List<PrincipalMaker>?,
        @SerialName("dating") val dating: Dating?,
        @SerialName("documentation") val documentation: List<String>?,
        @SerialName("dimensions") val dimensions: List<Dimension>?,
        @SerialName("webImage") val webImage: Image?,
    ) {
        @Serializable
        data class PrincipalMaker(
            @SerialName("name") val name: String?,
            @SerialName("dateOfBirth") val dateOfBirth: String?,
            @SerialName("dateOfDeath") val dateOfDeath: String?,
        )

        @Serializable
        data class Dating(
            @SerialName("presentingDate") val presentingDate: String?,
        )

        @Serializable
        data class Dimension(
            @SerialName("unit") val unit: String?,
            @SerialName("type") val type: String?,
            @SerialName("value") val value: String?,
        )

        @Serializable
        data class Image(
            @SerialName("guid") val guid: String?,
            @SerialName("width") val width: Int?,
            @SerialName("height") val height: Int?,
            @SerialName("url") val url: String?,
        )
    }
}

fun ArtObjectDetailResponse.toDomain(): ArtObjectDetail {
    val obj = requireNotNull(this.artObject) { "ArtObject cannot be null" }

    return ArtObjectDetail(
        objectNumber = obj.webImage?.guid.orEmpty(),
        title = obj.title.orEmpty(),
        description = obj.description.orEmpty(),
        objectCollection = obj.objectCollection ?: emptyList(),
        techniques = obj.techniques ?: emptyList(),
        principalMakers = obj.principalMakers?.map {
            ArtObjectDetail.PrincipalMaker(
                name = it.name.orEmpty(),
                dateOfBirth = it.dateOfBirth,
                dateOfDeath = it.dateOfDeath,
            )
        } ?: emptyList(),
        presentingDate = obj.dating?.presentingDate,
        documentation = obj.documentation ?: emptyList(),
        dimensions = obj.dimensions?.map {
            ArtObjectDetail.Dimension(
                unit = it.unit.orEmpty(),
                type = it.type.orEmpty(),
                value = it.value.orEmpty(),
            )
        } ?: emptyList(),
        image = obj.webImage?.let {
            val width = requireNotNull(it.width).toFloat()
            val height = requireNotNull(it.height).toFloat()
            val ratio = width / height
            ArtObjectDetail.Image(
                ratio = ratio,
                url = it.url.orEmpty(),
            )
        },
    )
}
