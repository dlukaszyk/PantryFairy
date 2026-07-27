package com.myapp.pantryfairy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapp.pantryfairy.data.dao.PantryItemDao
import com.myapp.pantryfairy.data.entity.PantryItemEntity

@Database(
    entities = [PantryItemEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pantryDao(): PantryItemDao
}

/*
Singleton = obiekt, którego w aplikacji istnieje tylko jedna sztuka.
jedna baza żeby cała aplikacja miała dostęp do tej samej instancji bazy > żeby nie było tak, że różne ekrany mają różne instancje
 */