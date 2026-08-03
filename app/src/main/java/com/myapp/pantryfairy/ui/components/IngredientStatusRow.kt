package com.myapp.pantryfairy.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun IngredientStatusRow(
    name: String,
    quantity: String,
    available: Boolean
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector =
                if (available) {
                    Icons.Default.CheckCircle
                } else {
                    Icons.Default.Warning
                },
            contentDescription = null,
            tint =
                if (available) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                }
        )


        Spacer(
            modifier = Modifier.width(12.dp)
        )


        Column {

            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = quantity,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        }
    }
}