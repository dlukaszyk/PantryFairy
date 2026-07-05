package com.myapp.pantryfairy.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.myapp.pantryfairy.screens.*
import androidx.navigation.compose.currentBackStackEntryAsState
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
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

            composable(AppScreen.Home.route) { HomeScreen() }
            composable(AppScreen.CookNow.route) { CookNowScreen() }
            composable(AppScreen.Recipes.route) { RecipesScreen() }
            composable(AppScreen.Pantry.route) { PantryScreen() }
            composable(AppScreen.MealPlan.route) { MealPlanScreen() }
            composable(AppScreen.Shopping.route) { ShoppingListScreen() }
            composable(AppScreen.Settings.route) { SettingsScreen() }
        }
    }
}