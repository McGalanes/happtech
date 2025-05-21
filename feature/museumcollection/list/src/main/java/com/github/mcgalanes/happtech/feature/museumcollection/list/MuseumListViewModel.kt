package com.github.mcgalanes.happtech.feature.museumcollection.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MuseumListViewModel(
    private val repository: RijksMuseumRepository,
) : ViewModel() {

    private val queryFlow = MutableStateFlow("France")

    val uiState: StateFlow<UiState> =
        combine(
            queryFlow.asStateFlow(),
            repository.getCollectionFlow(),
        ) { query, items -> query to items }
            .map { (query, items) ->
                UiState(
                    query = query,
                    items = items.toImmutableList(),
                )
            }.stateIn(
                scope = viewModelScope,
                started = WhileSubscribed(5_000),
                initialValue = UiState(),
            )

    init {
        viewModelScope.launch {
            repository.refreshAllCollection(queryFlow.value)
        }
    }

    fun onQueryChange(query: String) {
        queryFlow.value = query
    }

    fun onSearchClick() {
        viewModelScope.launch {
            repository.refreshAllCollection(queryFlow.value)
        }
    }
}
