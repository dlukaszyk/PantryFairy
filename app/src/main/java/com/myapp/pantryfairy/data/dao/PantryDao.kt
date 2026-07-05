package com.myapp.pantryfairy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.myapp.pantryfairy.data.entity.PantryItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PantryDao {

    @Query("SELECT * FROM pantry_items")
    fun getAll(): Flow<List<PantryItemEntity>>

    @Insert
    suspend fun insert(item: PantryItemEntity)

    @Update
    suspend fun update(item: PantryItemEntity)

    @Delete
    suspend fun delete(item: PantryItemEntity)
}