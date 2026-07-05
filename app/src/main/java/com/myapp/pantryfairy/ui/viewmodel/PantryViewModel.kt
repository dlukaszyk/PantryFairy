package com.myapp.pantryfairy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.pantryfairy.data.entity.PantryItemEntity
import com.myapp.pantryfairy.data.repository.PantryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PantryViewModel(
    private val repository: PantryRepository
) : ViewModel() {

    val pantryItems = repository.pantryItems
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addItem(name: String) {
        viewModelScope.launch {
            repository.addItem(name)
        }
    }

    fun toggleItem(item: PantryItemEntity) {
        viewModelScope.launch {
            repository.toggleItem(item)
        }
    }
}