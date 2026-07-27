package com.myapp.pantryfairy.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.myapp.pantryfairy.data.repository.PantryRepository
import com.myapp.pantryfairy.screens.*

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    pantryRepository: PantryRepository
) {

    val navController = rememberNavController()

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
                RecipesScreen()
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