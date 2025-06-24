package com.github.mcgalanes.happtech.feature.lemonde.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mcgalanes.happtech.feature.lemonde.list.component.ElementItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun LeMondeListScreen(
    modifier: Modifier = Modifier,
    viewModel: LeMondeListViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LeMondeListScreen(
        state = state,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LeMondeListScreen(
    state: UiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.feature_lemonde_list_title),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier.padding(horizontal = 24.dp),
            contentPadding = paddingValues,
        ) {
            item {
                Spacer(Modifier.height(16.dp))
            }

            itemsIndexed(items = state.elements) { index, element ->
                ElementItem(
                    modifier = Modifier.fillMaxWidth(),
                    element = element,
                )

                if (index < state.elements.lastIndex) {
                    Spacer(Modifier.height(16.dp))
                }
            }

            item {
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
