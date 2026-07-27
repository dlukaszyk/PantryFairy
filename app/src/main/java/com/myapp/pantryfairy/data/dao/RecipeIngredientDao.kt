package com.myapp.pantryfairy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.myapp.pantryfairy.data.entity.RecipeIngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeIngredientDao {

    @Query(
        "SELECT * FROM recipe_ingredients WHERE recipeId = :recipeId"
    )
    fun getIngredientsForRecipe(
        recipeId: Long
    ): Flow<List<RecipeIngredientEntity>>

    @Insert
    suspend fun insert(
        ingredient: RecipeIngredientEntity
    )

    @Insert
    suspend fun insertAll(
        ingredients: List<RecipeIngredientEntity>
    )

    @Query(
        "DELETE FROM recipe_ingredients WHERE recipeId = :recipeId"
    )
    suspend fun deleteForRecipe(
        recipeId: Long
    )
}