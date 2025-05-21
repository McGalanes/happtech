package com.github.mcgalanes.happtech.feature.museumcollection.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mcgalanes.happtech.core.design.HapptechTheme

@Composable
fun SearchTopAppBar(
    queryValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .padding(horizontal = 48.dp),
    ) {
        TextField(
            modifier = Modifier.align(Alignment.Center),
            value = queryValue,
            onValueChange = onValueChange,
        )
    }
}

@Preview
@Composable
private fun Preview_SearchTopAppBar() {
    HapptechTheme {
        SearchTopAppBar(
            queryValue = "France",
            onValueChange = {},
        )
    }
}
