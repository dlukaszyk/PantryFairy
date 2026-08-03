package com.myapp.pantryfairy.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CookStatusCard(
    canCook: Boolean,
    missingIngredients: List<String>
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector =
                        if (canCook) {
                            Icons.Default.CheckCircle
                        } else {
                            Icons.Default.Warning
                        },
                    contentDescription = null,
                    tint =
                        if (canCook) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        }
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text =
                        if (canCook) {
                            "You can cook this"
                        } else {
                            "Missing ingredients"
                        },
                    style = MaterialTheme.typography.titleMedium
                )
            }


            if (!canCook) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                missingIngredients.forEach { ingredient ->

                    Text(
                        text = "• $ingredient"
                    )

                }
            }
        }
    }
}