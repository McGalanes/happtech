package com.github.mcgalanes.happtech.feature.museumcollection.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MuseumDetailViewModel(
    private val objectNumber: String,
    private val repository: RijksMuseumRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun onSummaryHeaderClick() {
        _uiState.update {
            it.copy(expanded = !it.expanded)
        }
    }

    fun onRetryClick() {
        _uiState.update { it.copy(loading = true) }
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            repository.getArtObjectDetail(objectNumber)
                .onSuccess { artObjectDetail ->
                    _uiState.update {
                        it.copy(
                            artObject = artObjectDetail,
                            loading = false,
                        )
                    }
                }.onFailure {
                    _uiState.update {
                        it.copy(loading = false)
                    }
                }
        }
    }
}
