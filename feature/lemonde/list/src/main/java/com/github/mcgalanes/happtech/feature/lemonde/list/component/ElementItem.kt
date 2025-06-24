package com.github.mcgalanes.happtech.feature.lemonde.list.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mcgalanes.happtech.core.domain.model.LeMondeElement

@Composable
fun ElementItem(
    element: LeMondeElement,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = element.title,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
