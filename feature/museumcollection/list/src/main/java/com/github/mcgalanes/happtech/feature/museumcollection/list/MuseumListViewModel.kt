package com.github.mcgalanes.happtech.feature.museumcollection.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MuseumListViewModel(
    private val repository: RijksMuseumRepository,
) : ViewModel() {

    private val queryFlow = MutableStateFlow(UiState.Default.query)
    private val loadingFlow = MutableStateFlow(UiState.Default.loading)

    val uiState: StateFlow<UiState> = combine(
        queryFlow,
        repository.getCollectionFlow(),
        loadingFlow,
    ) { query, collection, loading ->
        Triple(
            query,
            collection,
            loading
        )
    }.map { (query, collection, loading) ->
        UiState(
            query = query,
            items = collection.toImmutableList(),
            loading = loading,
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5_000),
        initialValue = UiState.Default,
    )

    private val _uiEvents = Channel<UiEvent>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        refresh()
    }

    fun onQueryChange(query: String) {
        queryFlow.value = query
    }

    fun onSearchClick() {
        refresh()
    }

    private fun refresh() {
        loadingFlow.value = true

        viewModelScope.launch {
            repository.refreshCollection(queryFlow.value).onFailure {
                _uiEvents.send(
                    UiEvent.ShowSnackBar(
                        messageRes = R.string.feature_museumcollection_error_default
                    )
                )
            }
            loadingFlow.value = false
        }
    }
}
