package com.myapp.pantryfairy.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pantry_items")
data class PantryItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val has: Boolean = false,
    val quantity: Double? = null
)