package com.myapp.pantryfairy.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pantry")
data class PantryItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val has: Boolean = false,
    val quantity: Double? = null,
    val useQuantity: Boolean = false
)