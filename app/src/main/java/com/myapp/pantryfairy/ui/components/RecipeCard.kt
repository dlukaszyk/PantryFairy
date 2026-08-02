package com.myapp.pantryfairy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapp.pantryfairy.data.entity.RecipeEntity
import com.myapp.pantryfairy.data.entity.RecipeIngredientEntity


@Composable
fun RecipeCard(
    recipe: RecipeEntity,
    ingredients: List<RecipeIngredientEntity>,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                onClick()
            }
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = recipe.name
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "${recipe.mealType} • ${recipe.category}"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "${recipe.protein}P • ${recipe.fat}F • ${recipe.carbs}C • ${recipe.calories} kcal"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            //pokazuje pierwswze 3 składniki (nawet jeżeli jest ich w przepisie więcej)
            ingredients.take(3).forEach { ingredient ->

                Text(
                    text = "• ${ingredient.name}"
                )

            }
        }
    }
}