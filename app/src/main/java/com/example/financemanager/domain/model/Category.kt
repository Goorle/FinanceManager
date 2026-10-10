package com.example.financemanager.domain.model

import com.example.financemanager.R
import kotlinx.serialization.Serializable

@Serializable
enum class Category(
    val displayName: Int,
    val icon: Int,
    val types: List<TransactionType>
) {
    Restaurant(
        R.string.display_restaurant,
        R.drawable.food_vector,
        listOf(TransactionType.EXPENSE)
    ),

    TRANSPORT(
        R.string.display_transport,
        R.drawable.transport_vector,
        listOf(TransactionType.EXPENSE)
    ),

    MEDICINE(
        R.string.display_medicine,
        R.drawable.medicine_vector,
        listOf(TransactionType.EXPENSE)
    ),

    GROCERIES(
        R.string.display_groceries,
        R.drawable.products_vector,
        listOf(TransactionType.EXPENSE)
    ),

    RENT(
        R.string.display_rent,
        R.drawable.rent_vector,
        listOf(TransactionType.EXPENSE)
    ),

    GIFTS(
        R.string.display_gifts,
        R.drawable.presents_vector,
        listOf(
            TransactionType.EXPENSE,
            TransactionType.INCOME
        )
    ),

    SAVINGS(
        R.string.display_savings,
        R.drawable.savings_vector,
        listOf(TransactionType.INCOME)
    ),

    ENTERTAINMENT(
        R.string.display_entertainment,
        R.drawable.entertainment_vector,
        listOf(TransactionType.EXPENSE)
    ),

    SALARY(
        R.string.display_salary,
        R.drawable.salary_vector,
        listOf(TransactionType.INCOME)
    ),

    MORE(
        R.string.display_more,
        R.drawable.plus_vector,
        listOf(
            TransactionType.EXPENSE,
            TransactionType.INCOME
        )
    )
}

fun Category.supports(type: TransactionType): Boolean {
    return type in types
}