package com.myapp.pantryfairy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.myapp.pantryfairy.data.dao.PantryItemDao
import com.myapp.pantryfairy.data.dao.RecipeDao
import com.myapp.pantryfairy.data.dao.RecipeIngredientDao
import com.myapp.pantryfairy.data.entity.PantryItemEntity
import com.myapp.pantryfairy.data.entity.RecipeEntity
import com.myapp.pantryfairy.data.entity.RecipeIngredientEntity

@Database(
    entities = [
        PantryItemEntity::class,
        RecipeEntity::class,
        RecipeIngredientEntity::class
    ],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pantryDao(): PantryItemDao

    abstract fun recipeDao(): RecipeDao

    abstract fun recipeIngredientDao(): RecipeIngredientDao
}

val MIGRATION_2_3 = object : Migration(2, 3) {

    override fun migrate(db: SupportSQLiteDatabase) {

        db.execSQL(
            """
            ALTER TABLE recipes
            ADD COLUMN instructions TEXT NOT NULL DEFAULT ''
            """
        )

    }
}
/*
Singleton = obiekt, którego w aplikacji istnieje tylko jedna sztuka.
jedna baza żeby cała aplikacja miała dostęp do tej samej instancji bazy > żeby nie było tak, że różne ekrany mają różne instancje
 */