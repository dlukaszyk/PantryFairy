package com.myapp.pantryfairy.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    NavigationBar {

        NavigationBarItem(
            selected = currentRoute == AppScreen.Home.route,
            onClick = {
                navController.navigate(AppScreen.Home.route) {
                    popUpTo(AppScreen.Home.route) { inclusive = false }
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.CookNow.route,
            onClick = {
                navController.navigate(AppScreen.CookNow.route)
            },
            icon = { Icon(Icons.Default.RestaurantMenu, null) },
            label = { Text("Cook") }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.Recipes.route,
            onClick = {
                navController.navigate(AppScreen.Recipes.route)
            },
            icon = { Icon(Icons.Default.MenuBook, null) },
            label = { Text("Recipes") }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.Pantry.route,
            onClick = {
                navController.navigate(AppScreen.Pantry.route)
            },
            icon = { Icon(Icons.Default.Kitchen, null) },
            label = { Text("Pantry") }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.MealPlan.route,
            onClick = {
                navController.navigate(AppScreen.MealPlan.route)
            },
            icon = { Icon(Icons.Default.CalendarMonth, null) },
            label = { Text("Plan") }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.Shopping.route,
            onClick = {
                navController.navigate(AppScreen.Shopping.route)
            },
            icon = { Icon(Icons.Default.ShoppingCart, null) },
            label = { Text("Shop") }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.Settings.route,
            onClick = {
                navController.navigate(AppScreen.Settings.route)
            },
            icon = { Icon(Icons.Default.Settings, null) },
            label = { Text("Settings") }
        )
    }
}