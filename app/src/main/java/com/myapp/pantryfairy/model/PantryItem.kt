package com.myapp.pantryfairy.model

data class PantryItem(
    val name: String,
    val has: Boolean = false,
    val quantity: Double? = null,
    val unit: String? = null
)