package com.github.mcgalanes.happtech.feature.museumcollection.list.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mcgalanes.happtech.core.design.HapptechTheme
import com.github.mcgalanes.happtech.feature.museumcollection.list.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    queryValue: String,
    loading: Boolean,
    onValueChange: (String) -> Unit,
    onSearchKeyboardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val inputField =
        @Composable {
            SearchBarDefaults.InputField(
                onSearch = {
                    keyboardController?.hide()
                    onSearchKeyboardClick()
                },
                placeholder = { Text(stringResource(R.string.feature_museumcollection_list_search_hint)) },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    AnimatedVisibility(loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp,
                        )
                    }
                },
                expanded = false,
                onQueryChange = onValueChange,
                query = queryValue,
                onExpandedChange = {},
            )
        }

    Box(
        modifier = modifier,
    ) {
        SearchBar(
            modifier = Modifier.align(Alignment.Center),
            inputField = inputField,
            expanded = false,
            onExpandedChange = {},
            content = {},
        )
    }
}

@Preview
@Composable
private fun Preview_SearchTopAppBar() {
    HapptechTheme {
        SearchTopAppBar(
            queryValue = "France",
            loading = false,
            onValueChange = {},
            onSearchKeyboardClick = {},
        )
    }
}
