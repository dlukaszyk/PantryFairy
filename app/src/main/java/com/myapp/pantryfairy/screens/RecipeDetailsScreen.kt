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
import androidx.navigation.NavController
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.myapp.pantryfairy.data.repository.PantryRepository
import com.myapp.pantryfairy.ui.components.CookStatusCard
import com.myapp.pantryfairy.ui.components.IngredientStatusRow
import com.myapp.pantryfairy.ui.components.NutritionCard
import com.myapp.pantryfairy.ui.components.RecipeInstructions
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun RecipeDetailsScreen(
    recipeId: Long,
    repository: RecipeRepository,
    pantryRepository: PantryRepository,
    navController: NavController
) {

    val factory = RecipeDetailsViewModelFactory(repository)

    val viewModel: RecipeDetailsViewModel = viewModel(
        factory = factory
    )
    val recipe by viewModel.recipe.collectAsState()

    val pantryItems by pantryRepository
        .getAllItems()
        .collectAsState(initial = emptyList())

    val missingIngredients =
        recipe?.ingredients?.filter { ingredient ->
            pantryItems.none { item ->
                item.name.equals(
                    ingredient.name,
                    ignoreCase = true
                )
                        &&
                        item.has
            }
        } ?: emptyList()

    val canCook =
        missingIngredients.isEmpty()

    LaunchedEffect(recipeId) {
        viewModel.loadRecipe(recipeId)
    }


    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
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


            Row {

                NutritionCard(
                    label = "Protein",
                    value = "${data.recipe.protein} g"
                )

                NutritionCard(
                    label = "Fat",
                    value = "${data.recipe.fat} g"
                )

                NutritionCard(
                    label = "Carbs",
                    value = "${data.recipe.carbs} g"
                )

                NutritionCard(
                    label = "Calories",
                    value = "${data.recipe.calories}"
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )


            CookStatusCard(
                canCook = canCook,
                missingIngredients = missingIngredients.map {
                    it.name
                }
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
                val available =
                    pantryItems.any { item ->
                        item.name.equals(
                            ingredient.name,
                            ignoreCase = true
                        )
                                &&
                                item.has
                    }

                IngredientStatusRow(
                    name = ingredient.name,
                    quantity = "${ingredient.quantity ?: ""} ${ingredient.unit ?: ""}",
                    available = available
                )
            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )

            RecipeInstructions(
                instructions = data.recipe.instructions
            )
        }
    }
}