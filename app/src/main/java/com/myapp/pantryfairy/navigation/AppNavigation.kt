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
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {

            composable("home") { HomeScreen() }
            composable("cook") { CookNowScreen() }
            composable("recipes") { RecipesScreen() }
            composable("pantry") { PantryScreen() }
            composable("mealplan") { MealPlanScreen() }
            composable("shopping") { ShoppingListScreen() }
            composable("settings") { SettingsScreen() }        }
    }
}