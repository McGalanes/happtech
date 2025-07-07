package com.github.mcgalanes.happtech.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object MuseumListScreen : NavKey

@Serializable
data class MuseumDetailScreen(
    val objectNumber: String,
) : NavKey
