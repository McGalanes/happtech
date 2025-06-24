package com.github.mcgalanes.happtech.core.data.remote.response

import com.github.mcgalanes.happtech.core.domain.model.LeMondeElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeMondeLiveArticlesResponse(
    @SerialName("elements") val elements: List<Element>?,
) {
    @Serializable
    data class Element(
        @SerialName("titre") val title: String?,
    )
}

fun LeMondeLiveArticlesResponse.toDomain(): List<LeMondeElement> =
    requireNotNull(elements) { "Elements cannot be null" }
        .mapNotNull(LeMondeLiveArticlesResponse.Element::toDomain)


fun LeMondeLiveArticlesResponse.Element.toDomain(): LeMondeElement? =
    title?.let {
        LeMondeElement(title = it)
    }

