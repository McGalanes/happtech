package com.github.mcgalanes.happtech.feature.lemonde.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mcgalanes.happtech.core.domain.repository.LeMondeRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeMondeListViewModel(
    private val repository: LeMondeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getElements()
                .onSuccess { elements ->
                    _uiState.value = UiState(elements = elements.toImmutableList())
                }.onFailure { _ ->
                    //TODO: Handle error
                }
        }
    }
}
