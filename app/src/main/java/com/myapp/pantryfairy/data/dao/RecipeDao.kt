package com.myapp.pantryfairy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Transaction
import androidx.room.Update
import com.myapp.pantryfairy.data.entity.RecipeEntity
import com.myapp.pantryfairy.model.RecipeWithIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes ORDER BY name")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes")
    suspend fun getRecipesOnce(): List<RecipeEntity>

    @Insert
    suspend fun insert(recipe: RecipeEntity): Long

    @Update
    suspend fun update(recipe: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)

    @Transaction
    @Query("SELECT * FROM recipes")
    fun getRecipesWithIngredients(): Flow<List<RecipeWithIngredients>>

    @Transaction
    @Query(
        "SELECT * FROM recipes WHERE id = :id"
    )
    suspend fun getRecipeWithIngredients(
        id: Long
    ): RecipeWithIngredients
}