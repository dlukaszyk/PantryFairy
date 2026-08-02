package com.myapp.pantryfairy.data.repository

import com.myapp.pantryfairy.data.dao.RecipeDao
import com.myapp.pantryfairy.data.dao.RecipeIngredientDao
import com.myapp.pantryfairy.data.entity.RecipeEntity
import com.myapp.pantryfairy.data.entity.RecipeIngredientEntity
import com.myapp.pantryfairy.model.RecipeWithIngredients
import kotlinx.coroutines.flow.Flow

class RecipeRepository(
    private val recipeDao: RecipeDao,
    private val ingredientDao: RecipeIngredientDao
) {

    fun getAllRecipes(): Flow<List<RecipeEntity>> {
        return recipeDao.getAllRecipes()
    }

    suspend fun getRecipesOnce(): List<RecipeEntity> {
        return recipeDao.getRecipesOnce()
    }

    suspend fun insertRecipeWithIngredients(
        recipe: RecipeEntity,
        ingredients: List<RecipeIngredientEntity>
    ) {
        //zapis przepisu
        val recipeId = recipeDao.insert(recipe)
        //przypisanie ID przepisu do składników
        val ingredientsWithId = ingredients.map {
            it.copy(recipeId = recipeId)
        }
        //zapis składników
        ingredientDao.insertAll(ingredientsWithId)
    }


    suspend fun deleteRecipe(
        recipe: RecipeEntity
    ) {
        ingredientDao.deleteForRecipe(recipe.id)
        recipeDao.delete(recipe)
    }

    fun getIngredients(
        recipeId: Long
    ): Flow<List<RecipeIngredientEntity>> {

        return ingredientDao.getIngredientsForRecipe(recipeId)
    }

    fun getRecipesWithIngredients():
            Flow<List<RecipeWithIngredients>> {

        return recipeDao.getRecipesWithIngredients()
    }

    suspend fun getRecipeWithIngredients(recipeId: Long):
            RecipeWithIngredients {

        return recipeDao.getRecipeWithIngredients(recipeId)
    }
}