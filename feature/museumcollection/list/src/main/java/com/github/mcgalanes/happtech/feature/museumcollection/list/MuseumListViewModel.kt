package com.github.mcgalanes.happtech.feature.museumcollection.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MuseumListViewModel(
    private val repository: RijksMuseumRepository,
) : ViewModel() {

    val uiState: StateFlow<UiState> =
        repository.getCollectionFlow().map {
            UiState(
                items = it.toImmutableList(),
            )
        }.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5_000),
            initialValue = UiState(),
        )

    init {
        viewModelScope.launch {
            repository.refreshAllCollection()
        }
    }
}
