package com.myapp.pantryfairy.data.repository

import com.myapp.pantryfairy.data.dao.PantryDao
import com.myapp.pantryfairy.data.entity.PantryItemEntity

class PantryRepository(
    private val dao: PantryDao
) {

    val pantryItems = dao.getAll()

    suspend fun addItem(name: String) {
        dao.insert(PantryItemEntity(name = name))
    }

    suspend fun toggleItem(item: PantryItemEntity) {
        dao.update(item.copy(has = !item.has))
    }

    suspend fun deleteItem(item: PantryItemEntity) {
        dao.delete(item)
    }
}