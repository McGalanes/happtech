package com.github.mcgalanes.happtech.feature.museumcollection.list

import com.github.mcgalanes.happtech.core.domain.model.ArtObject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class UiState(
    val items: ImmutableList<ArtObject> = persistentListOf(),
)
