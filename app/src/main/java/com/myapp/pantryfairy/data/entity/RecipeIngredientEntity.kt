package com.myapp.pantryfairy.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipe_ingredients"
)
data class RecipeIngredientEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val recipeId: Long,
    val name: String,
    val quantity: Double? = null,
    val unit: String? = null
)