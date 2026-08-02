package com.myapp.pantryfairy.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.myapp.pantryfairy.R

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
            label = { Text(stringResource(R.string.home)) }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.CookNow.route,
            onClick = {
                navController.navigate(AppScreen.CookNow.route)
            },
            icon = { Icon(Icons.Default.RestaurantMenu, null) },
            label = { Text(stringResource(R.string.cook)) }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.Recipes.route,
            onClick = {
                navController.navigate(AppScreen.Recipes.route)
            },
            icon = { Icon(Icons.Default.MenuBook, null) },
            label = { Text(stringResource(R.string.recipes)) }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.Pantry.route,
            onClick = {
                navController.navigate(AppScreen.Pantry.route)
            },
            icon = { Icon(Icons.Default.Kitchen, null) },
            label = { Text(stringResource(R.string.pantry)) }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.MealPlan.route,
            onClick = {
                navController.navigate(AppScreen.MealPlan.route)
            },
            icon = { Icon(Icons.Default.CalendarMonth, null) },
            label = { Text(stringResource(R.string.plan)) }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.Shopping.route,
            onClick = {
                navController.navigate(AppScreen.Shopping.route)
            },
            icon = { Icon(Icons.Default.ShoppingCart, null) },
            label = { Text(stringResource(R.string.shop)) }
        )

        NavigationBarItem(
            selected = currentRoute == AppScreen.Settings.route,
            onClick = {
                navController.navigate(AppScreen.Settings.route)
            },
            icon = { Icon(Icons.Default.Settings, null) },
            label = { Text(stringResource(R.string.settings)) }
        )
    }
}