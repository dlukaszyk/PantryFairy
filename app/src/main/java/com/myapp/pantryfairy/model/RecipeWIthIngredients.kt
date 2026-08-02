package com.myapp.pantryfairy.model

import androidx.room.Embedded
import androidx.room.Relation
import com.myapp.pantryfairy.data.entity.*

data class RecipeWithIngredients(

    @Embedded
    val recipe: RecipeEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val ingredients: List<RecipeIngredientEntity>
)