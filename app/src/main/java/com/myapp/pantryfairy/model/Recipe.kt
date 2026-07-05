package com.myapp.pantryfairy.model

data class Recipe(
    val name: String,
    val ingredients: List<String>,
    val mealType: MealType,
    val category: RecipeCategory,
    val calories: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val carbs: Int = 0
)