package com.myapp.pantryfairy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.myapp.pantryfairy.data.entity.PantryItemEntity
import com.myapp.pantryfairy.data.repository.PantryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class PantryViewModel(
    private val repository: PantryRepository
) : ViewModel() {

    val pantryItems = repository.getAllItems()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


    fun addItem(item: PantryItemEntity) {
        viewModelScope.launch {
            repository.insert(item)
        }
    }


    fun updateItem(item: PantryItemEntity) {
        viewModelScope.launch {
            repository.update(item)
        }
    }


    fun deleteItem(item: PantryItemEntity) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }


    //test data - do usunięcia po dodaniu prawdziwych danych
    fun addTestDataIfNeeded() {
        viewModelScope.launch {

            if (repository.countItems() == 0) {

                repository.insert(
                    PantryItemEntity(
                        name = "Eggs",
                        has = true,
                        quantity = 6.0
                    )
                )

                repository.insert(
                    PantryItemEntity(
                        name = "Milk",
                        has = true,
                        quantity = 1.0
                    )
                )

                repository.insert(
                    PantryItemEntity(
                        name = "Flour",
                        has = false
                    )
                )
            }

        }

    }
}