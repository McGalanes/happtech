package com.github.mcgalanes.happtech.feature.museumcollection.detail

import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail

data class UiState(
    val artObject: ArtObjectDetail? = null,
    val expanded: Boolean = false,
    val loading: Boolean = true,
)
