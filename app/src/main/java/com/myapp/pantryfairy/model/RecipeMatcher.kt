package com.myapp.pantryfairy.model

fun matchRecipes(
    recipes: List<Recipe>,
    pantry: List<PantryItem>
): List<RecipeMatch> {

    return recipes.map { recipe ->

        val missing = recipe.ingredients.filter { ingredient ->
            pantry.none { it.name == ingredient && it.has }
        }

        RecipeMatch(
            recipe = recipe,
            missing = missing
        )
    }
}