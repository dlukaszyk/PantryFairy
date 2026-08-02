package com.myapp.pantryfairy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myapp.pantryfairy.R
import com.myapp.pantryfairy.data.repository.RecipeRepository
import com.myapp.pantryfairy.viewmodel.RecipeDetailsViewModel
import com.myapp.pantryfairy.viewmodel.RecipeDetailsViewModelFactory


@Composable
fun RecipeDetailsScreen(
    recipeId: Long,
    repository: RecipeRepository
) {

    val factory = RecipeDetailsViewModelFactory(repository)

    val viewModel: RecipeDetailsViewModel = viewModel(
        factory = factory
    )


    LaunchedEffect(recipeId) {
        viewModel.loadRecipe(recipeId)
    }


    val recipe by viewModel.recipe.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        if (recipe == null) {

            Text(
                text = stringResource(R.string.loading)
            )

        } else {

            val data = recipe!!

            Text(
                text = data.recipe.name,
                style = MaterialTheme.typography.headlineMedium
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            Text(
                text = "${data.recipe.mealType} • ${data.recipe.category}"
            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            Text(
                text = stringResource(R.string.nutrition)
            )


            Text(
                text = "${data.recipe.protein}P • ${data.recipe.fat}F • ${data.recipe.carbs}C • ${data.recipe.calories} kcal"
            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            Text(
                text = stringResource(R.string.ingredients),
                style = MaterialTheme.typography.titleMedium
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            data.ingredients.forEach { ingredient ->

                Text(
                    text = "• ${ingredient.name} ${ingredient.quantity ?: ""} ${ingredient.unit ?: ""}"
                )

            }
        }
    }
}