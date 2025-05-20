package com.github.mcgalanes.happtech.feature.museumcollection.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mcgalanes.happtech.core.design.HapptechTheme
import com.github.mcgalanes.happtech.core.design.util.isLargeScreen
import com.github.mcgalanes.happtech.feature.museumcollection.list.component.ArtObjectItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun MuseumListScreen(
    onItemClick: (objectNumber: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MuseumListViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    MuseumListScreen(
        modifier = modifier,
        state = state,
        onItemClick = onItemClick,
    )
}

@Composable
private fun MuseumListScreen(
    state: UiState,
    onItemClick: (objectNumber: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) { padding ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = padding,
            verticalItemSpacing = 16.dp,
            columns = StaggeredGridCells.Fixed(
                if (isLargeScreen()) 3 else 2
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = state.items) {
                ArtObjectItem(
                    modifier = Modifier.wrapContentHeight(),
                    artObject = it,
                    onClick = { onItemClick(it.objectNumber) },
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview_MuseumListScreen() {
    HapptechTheme {
        MuseumListScreen(
            modifier = Modifier.fillMaxSize(),
            state = UiState(),
            onItemClick = {},
        )
    }
}
