package com.github.mcgalanes.happtech.feature.museumcollection.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.github.mcgalanes.happtech.core.design.HapptechTheme
import com.github.mcgalanes.happtech.core.design.util.isLargeScreen
import com.github.mcgalanes.happtech.feature.museumcollection.list.component.ArtObjectItem
import com.github.mcgalanes.happtech.feature.museumcollection.list.component.SearchTopAppBar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MuseumListScreen(
    navToDetail: (objectNumber: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MuseumListViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val lifecycleOwner = LocalLifecycleOwner.current
    val resources = LocalContext.current.resources

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(state = State.RESUMED) {
            viewModel.uiEvents.collect { event ->
                when (event) {
                    is UiEvent.ShowSnackBar -> {
                        snackbarHostState.showSnackbar(
                            message = resources.getString(event.messageRes),
                            withDismissAction = true,
                        )
                    }
                }
            }
        }
    }

    MuseumListScreen(
        modifier = modifier,
        state = state,
        snackbarHostState = snackbarHostState,
        onItemClick = navToDetail,
        onQueryChange = viewModel::onQueryChange,
        onSearchClick = viewModel::onSearchClick,
    )
}

@Composable
private fun MuseumListScreen(
    state: UiState,
    snackbarHostState: SnackbarHostState,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onItemClick: (objectNumber: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyStaggeredGridState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            SearchTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                queryValue = state.query,
                onValueChange = onQueryChange,
                onSearchKeyboardClick = {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                    onSearchClick()
                },
            )
        }) { padding ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(
                top = padding.calculateTopPadding() + 32.dp,
                bottom = padding.calculateBottomPadding() + 24.dp,
            ),
            verticalItemSpacing = 16.dp,
            columns = StaggeredGridCells.Fixed(if (isLargeScreen()) 3 else 2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            state = listState,
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
            snackbarHostState = remember { SnackbarHostState() },
            onQueryChange = {},
            onItemClick = {},
            onSearchClick = {},
        )
    }
}
