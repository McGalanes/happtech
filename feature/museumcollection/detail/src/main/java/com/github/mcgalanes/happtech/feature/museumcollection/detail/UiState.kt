package com.github.mcgalanes.happtech.feature.museumcollection.detail

import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail

data class UiState(
    val artObject: ArtObjectDetail,
    val expanded: Boolean,
)
