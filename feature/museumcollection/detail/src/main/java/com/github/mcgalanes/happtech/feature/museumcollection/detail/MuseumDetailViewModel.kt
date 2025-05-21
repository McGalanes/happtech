package com.github.mcgalanes.happtech.feature.museumcollection.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import com.github.mcgalanes.happtech.core.navigation.NavScreen.MuseumDetail.Companion.ARG_OBJECT_NUMBER
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MuseumDetailViewModel(
    handle: SavedStateHandle,
    private val repository: RijksMuseumRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val objectNumber: String = requireNotNull(handle[ARG_OBJECT_NUMBER])

    init {
        viewModelScope.launch {
            repository.getArtObjectDetail(objectNumber)
                .onSuccess { artObjectDetail ->
                    _uiState.update {
                        it.copy(
                            artObject = artObjectDetail,
                            loading = false,
                        )
                    }
                }
                .onFailure {
                    _uiState.update {
                        it.copy(loading = false)
                    }
                }
        }
    }

    fun onSummaryHeaderClick() {
        _uiState.update {
            it.copy(expanded = !it.expanded)
        }
    }
}
