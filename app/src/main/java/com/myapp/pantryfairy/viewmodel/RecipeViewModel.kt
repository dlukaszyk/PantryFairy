package com.myapp.pantryfairy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.pantryfairy.data.entity.RecipeEntity
import com.myapp.pantryfairy.data.entity.RecipeIngredientEntity
import com.myapp.pantryfairy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val repository: RecipeRepository
) : ViewModel() {


    val recipes = repository.getRecipesWithIngredients()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    private val _selectedIngredients = MutableStateFlow<List<RecipeIngredientEntity>>(emptyList())

    val selectedIngredients = _selectedIngredients.asStateFlow()

    fun deleteRecipe(recipe: RecipeEntity) {

        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }
    }

    fun loadIngredients(recipeId: Long) {
        viewModelScope.launch {
            repository.getIngredients(recipeId)
                .collect { ingredients ->
                    _selectedIngredients.value = ingredients
                }
        }
    }
}