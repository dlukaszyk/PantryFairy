package com.myapp.pantryfairy.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.myapp.pantryfairy.data.repository.PantryRepository
import com.myapp.pantryfairy.data.repository.RecipeRepository
import com.myapp.pantryfairy.data.seed.DatabaseSeeder
import com.myapp.pantryfairy.screens.*

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    pantryRepository: PantryRepository,
    recipeRepository: RecipeRepository
) {

    val navController = rememberNavController()
    val seeder = remember {
        DatabaseSeeder(
            pantryRepository,
            recipeRepository
        )
    }
    LaunchedEffect(Unit) {
        seeder.seedPantryIfEmpty()
        seeder.seedRecipesIfEmpty()
    }

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.Home.route,
            modifier = Modifier.padding(padding)
        ) {

            composable(AppScreen.Home.route) {
                HomeScreen()
            }

            composable(AppScreen.CookNow.route) {
                CookNowScreen()
            }

            composable(AppScreen.Recipes.route) {
                RecipesScreen(
                    repository = recipeRepository,
                    navController = navController
                )
            }

            composable(
                route = AppScreen.RecipeDetails.route
            ) {

                val recipeId = it.arguments
                    ?.getString("recipeId")
                    ?.toLongOrNull()


                if (recipeId != null) {

                    RecipeDetailsScreen(
                        recipeId = recipeId,
                        repository = recipeRepository,
                        pantryRepository = pantryRepository,
                        navController = navController
                    )

                }

            }

            composable(AppScreen.Pantry.route) {
                PantryScreen(
                    repository = pantryRepository
                )
            }

            composable(AppScreen.MealPlan.route) {
                MealPlanScreen()
            }

            composable(AppScreen.Shopping.route) {
                ShoppingListScreen()
            }

            composable(AppScreen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}