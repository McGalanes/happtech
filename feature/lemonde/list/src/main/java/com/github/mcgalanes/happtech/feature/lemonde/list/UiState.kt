package com.github.mcgalanes.happtech.feature.lemonde.list

import com.github.mcgalanes.happtech.core.domain.model.LeMondeElement
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class UiState(
    val elements: ImmutableList<LeMondeElement> = persistentListOf(),
)
