package com.myapp.pantryfairy.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.myapp.pantryfairy.screens.*
import androidx.navigation.compose.currentBackStackEntryAsState
import com.myapp.pantryfairy.model.PantryItem
import com.myapp.pantryfairy.database.recipes

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    val pantry = remember {
        mutableStateListOf(
            PantryItem("eggs", true, 6.0),
            PantryItem("milk", true),
            PantryItem("flour", false)
        )
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

            composable(AppScreen.Home.route) { HomeScreen(recipes, pantry) }
            composable(AppScreen.CookNow.route) { CookNowScreen(recipes, pantry) }
            composable(AppScreen.Recipes.route) { RecipesScreen() }
            composable(AppScreen.Pantry.route) { PantryScreen() }
            composable(AppScreen.MealPlan.route) { MealPlanScreen() }
            composable(AppScreen.Shopping.route) { ShoppingListScreen() }
            composable(AppScreen.Settings.route) { SettingsScreen() }
        }
    }
}