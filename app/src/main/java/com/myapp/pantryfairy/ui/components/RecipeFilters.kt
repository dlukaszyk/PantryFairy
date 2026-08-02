package com.myapp.pantryfairy.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterAltOff
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.myapp.pantryfairy.R


@Composable
fun RecipeFilters(
    selectedMealType: String,
    selectedCategory: String,
    onMealTypeChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onClear: () -> Unit
) {

    val mealTypes = listOf(
        "All",
        "Breakfast",
        "Lunch",
        "Dinner"
    )

    val categories = listOf(
        "All",
        "Sweet",
        "Savory"
    )


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(R.string.filters),
                    style = MaterialTheme.typography.titleMedium
                )


                IconButton(
                    onClick = onClear
                ) {

                    Icon(
                        imageVector = Icons.Default.FilterAltOff,
                        contentDescription = stringResource(R.string.clear_filters)
                    )

                }

            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(stringResource(R.string.meal_type))

            Row {

                mealTypes.forEach { type ->

                    FilterChip(
                        selected = selectedMealType == type,
                        onClick = {
                            onMealTypeChange(type)
                        },
                        label = {
                            Text(type)
                        }
                    )

                }
            }

            if (selectedMealType != "All" || selectedCategory != "All") {

            }

            Text(stringResource(R.string.category))

            Row {

                categories.forEach { category ->

                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = {
                            onCategoryChange(category)
                        },
                        label = {
                            Text(category)
                        }
                    )

                }
            }
        }
    }

}