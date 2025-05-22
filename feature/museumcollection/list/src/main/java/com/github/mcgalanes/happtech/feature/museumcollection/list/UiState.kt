package com.github.mcgalanes.happtech.feature.museumcollection.list

import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class UiState(
    val query: String,
    val items: ImmutableList<ArtObjectLight>,
    val loading: Boolean,
) {
    companion object {
        val Default = UiState(
            query = "",
            items = persistentListOf(),
            loading = true,
        )
    }
}
