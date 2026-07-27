package com.myapp.pantryfairy.data.repository

import com.myapp.pantryfairy.data.dao.PantryItemDao
import com.myapp.pantryfairy.data.entity.PantryItemEntity
import kotlinx.coroutines.flow.Flow

class PantryRepository(
    private val dao: PantryItemDao
) {

    suspend fun countItems(): Int {
        return dao.countItems()
    }

    fun getAllItems(): Flow<List<PantryItemEntity>> {
        return dao.getAllItems()
    }

    suspend fun insert(item: PantryItemEntity) {
        dao.insert(item)
    }

    suspend fun update(item: PantryItemEntity) {
        dao.update(item)
    }

    suspend fun delete(item: PantryItemEntity) {
        dao.delete(item)
    }
}