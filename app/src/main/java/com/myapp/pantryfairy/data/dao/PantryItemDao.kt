package com.myapp.pantryfairy.data.dao

import androidx.room.*
import com.myapp.pantryfairy.data.entity.PantryItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
/*
DAO = Data Access Object
obiekt, który odpowiada za komunikację z bazą danych.
*/

interface PantryItemDao {

    @Query("SELECT COUNT(*) FROM pantry")
    suspend fun countItems(): Int

    @Query("SELECT * FROM pantry ORDER BY name")
    fun getAllItems(): Flow<List<PantryItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: PantryItemEntity)

    @Update
    suspend fun update(item: PantryItemEntity)

    @Delete
    suspend fun delete(item: PantryItemEntity)
}