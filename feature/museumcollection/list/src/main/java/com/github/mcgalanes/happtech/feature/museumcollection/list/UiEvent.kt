package com.github.mcgalanes.happtech.feature.museumcollection.list

import androidx.annotation.StringRes

sealed class UiEvent {
    data class ShowSnackBar(@StringRes val messageRes: Int) : UiEvent()
}
