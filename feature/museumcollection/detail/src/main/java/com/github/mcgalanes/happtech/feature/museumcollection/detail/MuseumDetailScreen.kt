package com.github.mcgalanes.happtech.feature.museumcollection.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mcgalanes.happtech.core.design.util.isLargeScreen
import com.github.mcgalanes.happtech.feature.museumcollection.detail.component.ImageCard
import com.github.mcgalanes.happtech.feature.museumcollection.detail.component.MuseumDetailBottomBar
import com.github.mcgalanes.happtech.feature.museumcollection.detail.component.SummaryCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun MuseumDetailScreen(
    onNavUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MuseumDetailViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    MuseumDetailScreen(
        state = state,
        onSummaryCardClick = viewModel::onSummaryHeaderClick,
        onNavUpClick = onNavUp,
        modifier = modifier,
    )
}

@Composable
private fun MuseumDetailScreen(
    state: UiState,
    onSummaryCardClick: () -> Unit,
    onNavUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        bottomBar = {
            MuseumDetailBottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .navigationBarsPadding()
                    .displayCutoutPadding(),
                onNavUpClick = onNavUpClick,
            )
        },
    ) { padding ->
        when {
            state.loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            state.artObject != null -> {
                val image = state.artObject.image

                if (isLargeScreen()) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(horizontal = 24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ImageCard(
                            modifier = Modifier.padding(32.dp),
                            image = image,
                        )

                        SummaryCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            expanded = state.expanded,
                            artObject = state.artObject,
                            onHeaderClick = onSummaryCardClick,
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        ImageCard(
                            modifier = Modifier
                                .weight(1f)
                                .padding(32.dp),
                            image = image,
                        )

                        SummaryCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            expanded = state.expanded,
                            artObject = state.artObject,
                            onHeaderClick = onSummaryCardClick,
                        )
                    }
                }
            }

            else -> {
                //TODO: show retry ?
            }
        }
    }
}

