package com.myapp.pantryfairy.data.seed

import com.myapp.pantryfairy.data.entity.PantryItemEntity
import com.myapp.pantryfairy.data.entity.RecipeEntity
import com.myapp.pantryfairy.data.entity.RecipeIngredientEntity
import com.myapp.pantryfairy.data.repository.PantryRepository
import com.myapp.pantryfairy.data.repository.RecipeRepository

class DatabaseSeeder(
    private val pantryRepository: PantryRepository,
    private val recipeRepository: RecipeRepository
) {

    suspend fun seedPantryIfEmpty() {

        val items = pantryRepository.getItemsOnce()
        if (items.isEmpty()) {
            pantryRepository.insert(
                PantryItemEntity(
                    name = "eggs",
                    has = true,
                    quantity = 6.0
                )
            )
            pantryRepository.insert(
                PantryItemEntity(
                    name = "milk",
                    has = true,
                    quantity = 1.0
                )
            )
            pantryRepository.insert(
                PantryItemEntity(
                    name = "flour",
                    has = false
                )
            )
        }
    }

    suspend fun seedRecipesIfEmpty() {
        val recipes = recipeRepository.getRecipesOnce()
        if (recipes.isEmpty()) {
            val pancakes = RecipeEntity(
                name = "Pancakes",
                mealType = "Breakfast",
                category = "Sweet",
                calories = 450,
                protein = 15,
                fat = 12,
                carbs = 60,
                instructions = """
                1. Mix eggs and milk.
                2. Add flour and combine.
                3. Fry pancakes on a pan.
                """.trimIndent()
            )
            val scrambledEggs = RecipeEntity(
                name = "Scrambled Eggs",
                mealType = "Dinner",
                category = "Savory",
                calories = 450,
                protein = 23,
                fat = 12,
                carbs = 12,
                instructions = """
                1. Crack eggs and scramble.
                """.trimIndent()
            )

            recipeRepository.insertRecipeWithIngredients(
                pancakes,
                listOf(
                    RecipeIngredientEntity(
                        recipeId = 0,
                        name = "eggs",
                        quantity = 2.0,
                        unit = "pcs"
                    ),
                    RecipeIngredientEntity(
                        recipeId = 0,
                        name = "milk",
                        quantity = 200.0,
                        unit = "ml"
                    ),
                    RecipeIngredientEntity(
                        recipeId = 0,
                        name = "flour",
                        quantity = 150.0,
                        unit = "g"
                    )
                )
            )
            recipeRepository.insertRecipeWithIngredients(
                scrambledEggs,
                listOf(
                    RecipeIngredientEntity(
                        recipeId = 0,
                        name = "eggs",
                        quantity = 5.0,
                        unit = "pcs"
                    )
                )
            )
        }
    }
}