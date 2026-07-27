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
                .fallbackToDestructiveMigration() //todo poprawić żeby nie znikały dane jak będą już prawdziwe
                .build()

            INSTANCE = instance
            instance
        }
    }
}