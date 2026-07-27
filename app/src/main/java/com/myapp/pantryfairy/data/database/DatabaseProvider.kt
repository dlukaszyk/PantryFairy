package com.myapp.pantryfairy.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: PantryDatabase? = null


    fun getDatabase(context: Context): PantryDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                PantryDatabase::class.java,
                "pantryfairy_database"
            ).build()

            INSTANCE = instance
            instance
        }
    }
}