package com.github.mcgalanes.happtech.core.design.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.mcgalanes.happtech.core.design.R

@Composable
fun GenericError(
    labelColor: Color,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.core_design_component_error_label),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = labelColor,
            )

            VerticalSpacer(16.dp)

            Surface(
                modifier = Modifier.size(40.dp),
                onClick = onRetryClick,
                shape = CircleShape,
                shadowElevation = 2.dp,
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = Icons.Default.Refresh,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = stringResource(R.string.core_design_component_error_refresh_content_description),
                )
            }
        }
    }
}
