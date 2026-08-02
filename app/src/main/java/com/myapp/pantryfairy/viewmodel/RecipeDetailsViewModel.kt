package com.myapp.pantryfairy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.pantryfairy.data.repository.RecipeRepository
import com.myapp.pantryfairy.model.RecipeWithIngredients
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class RecipeDetailsViewModel(
    private val repository: RecipeRepository
) : ViewModel() {


    private val _recipe =
        MutableStateFlow<RecipeWithIngredients?>(null)

    val recipe =
        _recipe.asStateFlow()


    fun loadRecipe(id: Long) {

        viewModelScope.launch {

            _recipe.value =
                repository.getRecipeWithIngredients(id)

        }

    }
}