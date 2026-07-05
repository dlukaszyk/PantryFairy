package com.myapp.pantryfairy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapp.pantryfairy.data.repository.PantryRepository

class PantryViewModelFactory(
    private val repository: PantryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PantryViewModel(repository) as T
    }
}