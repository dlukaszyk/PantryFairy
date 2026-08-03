package com.myapp.pantryfairy.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null


    fun getDatabase(context: Context): AppDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "pantryfairy_database"
            )
                .addMigrations(
                    MIGRATION_2_3
                )
                .build()

            INSTANCE = instance
            instance
        }
    }
}