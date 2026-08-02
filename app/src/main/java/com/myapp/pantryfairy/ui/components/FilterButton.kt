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
import androidx.compose.ui.unit.dp

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
                contentDescription = "Filters"
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = if (filters.isEmpty()) {
                    "Filters"
                } else {
                    "Filters (${filters.size})"
                }
            )
        }

        if (filters.isNotEmpty()) {

            TextButton(
                onClick = onClear
            ) {
                Text(
                    text = "Clear filters"
                )
                Icon(
                    imageVector = Icons.Default.FilterAltOff,
                    contentDescription = "Clear filters"
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