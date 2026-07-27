package com.myapp.pantryfairy.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myapp.pantryfairy.data.entity.RecipeEntity
import com.myapp.pantryfairy.data.repository.RecipeRepository
import com.myapp.pantryfairy.viewmodel.RecipeViewModel
import com.myapp.pantryfairy.viewmodel.RecipeViewModelFactory


@Composable
fun RecipesScreen(
    repository: RecipeRepository
) {

    val factory = RecipeViewModelFactory(repository)
    val viewModel: RecipeViewModel = viewModel(
        factory = factory
    )
    val recipes by viewModel.recipes.collectAsState()
    var selectedRecipe by remember {
        mutableStateOf<RecipeEntity?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Recipes"
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn {
            items(recipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = {

                        selectedRecipe = recipe
                        viewModel.loadIngredients(recipe.id)

                    }
                )

            }

        }
        selectedRecipe?.let {

            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = "Ingredients:"
            )
            val ingredients by viewModel.selectedIngredients.collectAsState()
            ingredients.forEach { ingredient ->
                Text(
                    text = "- ${ingredient.name} ${ingredient.quantity ?: ""} ${ingredient.unit ?: ""}"
                )
            }
        }
    }
}


@Composable
fun RecipeCard(
    recipe: RecipeEntity,
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
        }
    }
}