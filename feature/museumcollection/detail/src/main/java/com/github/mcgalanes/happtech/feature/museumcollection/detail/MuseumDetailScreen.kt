package com.github.mcgalanes.happtech.feature.museumcollection.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.mcgalanes.happtech.feature.museumcollection.detail.component.SummaryCard

@Composable
fun MuseumDetailScreen(
    state: UiState,
    onSummaryCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            SummaryCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                expanded = state.expanded,
                artObject = state.artObject,
                onHeaderClick = onSummaryCardClick,
            )
        }
    }
}
