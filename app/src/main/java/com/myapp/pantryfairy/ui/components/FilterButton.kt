package com.myapp.pantryfairy.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAltOff
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.myapp.pantryfairy.R

@Composable
fun FilterButton(
    filters: List<String>,
    onClick: () -> Unit,
    onClear: () -> Unit
) {

    Column {

        Button(
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = stringResource(R.string.filters)
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = if (filters.isEmpty()) {
                    stringResource(R.string.filters)
                } else {
                    "${stringResource(R.string.filters)} (${filters.size})"
                }
            )
        }

        if (filters.isNotEmpty()) {

            TextButton(
                onClick = onClear
            ) {
                Text(
                    text = stringResource(R.string.clear_filters)
                )
                Icon(
                    imageVector = Icons.Default.FilterAltOff,
                    contentDescription = stringResource(R.string.clear_filters)
                )


            }

        }
        if (filters.isNotEmpty()) {

            Text(
                text = filters
                    .take(3)
                    .joinToString(" • ")
            )

        }
    }
}