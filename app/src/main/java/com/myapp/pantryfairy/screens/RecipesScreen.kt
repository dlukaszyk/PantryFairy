package com.myapp.pantryfairy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myapp.pantryfairy.data.repository.RecipeRepository
import com.myapp.pantryfairy.ui.components.RecipeCard
import com.myapp.pantryfairy.viewmodel.RecipeViewModel
import com.myapp.pantryfairy.viewmodel.RecipeViewModelFactory
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.myapp.pantryfairy.ui.components.FilterButton
import com.myapp.pantryfairy.ui.components.RecipeFilters
import androidx.compose.animation.AnimatedVisibility
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import com.myapp.pantryfairy.R
import com.myapp.pantryfairy.model.RecipeWithIngredients

@Composable
fun RecipesScreen(
    repository: RecipeRepository,
    navController: NavController
) {

    val factory = RecipeViewModelFactory(repository)
    val viewModel: RecipeViewModel = viewModel(
        factory = factory
    )
    val recipes by viewModel.recipes.collectAsState()
    var searchQuery by remember {
        mutableStateOf("")
    }

    var selectedMealType by remember {
        mutableStateOf("All")
    }

    var selectedCategory by remember {
        mutableStateOf("All")
    }

    val activeFilters = buildList {

        if (searchQuery.isNotBlank()) {
            add(searchQuery)
        }

        if (selectedMealType != "All") {
            add(selectedMealType)
        }

        if (selectedCategory != "All") {
            add(selectedCategory)
        }

    }

    var showFilters by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = stringResource(R.string.recipes)
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            label = {
                Text(stringResource(R.string.search_recipes))
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        FilterButton(
            filters = activeFilters,

            onClick = {
                showFilters = !showFilters
            },

            onClear = {

                searchQuery = ""
                selectedMealType = "All"
                selectedCategory = "All"

            }
        )
        AnimatedVisibility(
            visible = showFilters
        ) {

            RecipeFilters(
                selectedMealType = selectedMealType,
                selectedCategory = selectedCategory,

                onMealTypeChange = {
                    selectedMealType = it
                },

                onCategoryChange = {
                    selectedCategory = it
                },

                onClear = {

                    searchQuery = ""
                    selectedMealType = "All"
                    selectedCategory = "All"

                }
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        val filteredRecipes = recipes.filter { recipe ->

            val query = searchQuery.lowercase()

            val matchesSearch =
                recipe.recipe.name.lowercase()
                    .contains(query)
                        ||
                        recipe.recipe.category.lowercase()
                            .contains(query)
                        ||
                        recipe.recipe.mealType.lowercase()
                            .contains(query)


            val matchesMealType =
                selectedMealType == "All"
                        ||
                        recipe.recipe.mealType == selectedMealType


            val matchesCategory =
                selectedCategory == "All"
                        ||
                        recipe.recipe.category == selectedCategory


            matchesSearch &&
                    matchesMealType &&
                    matchesCategory
        }

        LazyColumn {
            items(filteredRecipes) { recipeWithIngredients ->
                RecipeCard(
                    recipe = recipeWithIngredients.recipe,
                    ingredients = recipeWithIngredients.ingredients,
                    onClick = {
                        navController.navigate(
                            "recipe_details/${recipeWithIngredients.recipe.id}"
                        )
                    }                )

            }

        }

    }
}