package com.github.mcgalanes.happtech.core.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class NavScreen {
    @Serializable
    data object MuseumList : NavScreen()

    @Serializable
    data class MuseumDetail(
        @SerialName(ARG_OBJECT_NUMBER) val objectNumber: String,
    ) : NavScreen() {
        companion object {
            const val ARG_OBJECT_NUMBER = "arg:object_number"
        }
    }
}
