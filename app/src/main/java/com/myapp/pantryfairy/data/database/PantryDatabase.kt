package com.myapp.pantryfairy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapp.pantryfairy.data.dao.PantryItemDao
import com.myapp.pantryfairy.data.entity.PantryItemEntity

@Database(
    entities = [PantryItemEntity::class],
    version = 1
)
abstract class PantryDatabase : RoomDatabase() {

    abstract fun pantryDao(): PantryItemDao
}