package com.myapp.pantryfairy.data

import com.myapp.pantryfairy.model.MealType
import com.myapp.pantryfairy.model.Recipe
import com.myapp.pantryfairy.model.RecipeCategory

val recipes = listOf(
    Recipe(
        name = "Omelette",
        ingredients = listOf("eggs", "milk"),
        mealType = MealType.BREAKFAST,
        category = RecipeCategory.SAVORY,
        calories = 250,
        protein = 18,
        fat = 18,
        carbs = 2
    ),
    Recipe(
        name = "Pancakes",
        ingredients = listOf("eggs", "milk", "flour"),
        mealType = MealType.BREAKFAST,
        category = RecipeCategory.SWEET,
        calories = 400,
        protein = 12,
        fat = 10,
        carbs = 60
    ),
    Recipe(
        name = "Scrambled Eggs",
        ingredients = listOf("eggs"),
        mealType = MealType.BREAKFAST,
        category = RecipeCategory.SAVORY,
        calories = 200,
        protein = 14,
        fat = 15,
        carbs = 1
    ),
    Recipe(
        name = "Pasta",
        ingredients = listOf("pasta", "tomato"),
        mealType = MealType.DINNER,
        category = RecipeCategory.SAVORY,
        calories = 500,
        protein = 15,
        fat = 10,
        carbs = 80
    )
)