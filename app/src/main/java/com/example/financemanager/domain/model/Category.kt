package com.example.financemanager.domain.model

import com.example.financemanager.R
import kotlinx.serialization.Serializable

@Serializable
enum class Category(
    val displayName: String,
    val icon: Int
) {
    FOOD("Food", R.drawable.food_vector),
    TRANSPORT("Transport", R.drawable.transport_vector),
    MEDICINE("Medicine", R.drawable.medicine_vector),
    GROCERIES("Groceries", R.drawable.products_vector),
    RENT("Rent", R.drawable.rent_vector),
    GIFTS("Gifts", R.drawable.presents_vector),
    SAVINGS("Savings", R.drawable.savings_vector),
    ENTERTAINMENT("Entertainment", R.drawable.entertainment_vector),
    MORE("More", R.drawable.plus_vector)
}