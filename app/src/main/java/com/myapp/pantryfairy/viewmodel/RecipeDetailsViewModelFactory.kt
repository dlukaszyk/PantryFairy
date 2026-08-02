package com.myapp.pantryfairy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapp.pantryfairy.data.repository.RecipeRepository


class RecipeDetailsViewModelFactory(
    private val repository: RecipeRepository
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(
                RecipeDetailsViewModel::class.java
            )
        ) {

            return RecipeDetailsViewModel(repository) as T

        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}