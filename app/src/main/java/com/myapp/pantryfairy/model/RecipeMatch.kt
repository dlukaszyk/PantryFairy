package com.myapp.pantryfairy.model

data class RecipeMatch(
    val recipe: Recipe,
    val missing: List<PantryItem>
) {
    val canCook: Boolean
        get() = missing.isEmpty()
}