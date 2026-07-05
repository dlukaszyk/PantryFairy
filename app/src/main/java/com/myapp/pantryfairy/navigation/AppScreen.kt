package com.myapp.pantryfairy.navigation

sealed class AppScreen(val route: String) {

    object Home : AppScreen("home")

    object CookNow : AppScreen("cook_now")

    object Recipes : AppScreen("recipes")

    object Pantry : AppScreen("pantry")

    object MealPlan : AppScreen("meal_plan")

    object Shopping : AppScreen("shopping")

    object Settings : AppScreen("settings")
}