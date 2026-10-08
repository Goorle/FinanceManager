package com.example.financemanager.domain.model

import com.example.financemanager.R
import kotlinx.serialization.Serializable

@Serializable
enum class Category(
    val displayName: String,
    val icon: Int,
    val types: List<TransactionType>
) {
    FOOD(
        "Food",
        R.drawable.food_vector,
        listOf(TransactionType.EXPENSE)
    ),

    TRANSPORT(
        "Transport",
        R.drawable.transport_vector,
        listOf(TransactionType.EXPENSE)
    ),

    MEDICINE(
        "Medicine",
        R.drawable.medicine_vector,
        listOf(TransactionType.EXPENSE)
    ),

    GROCERIES(
        "Groceries",
        R.drawable.products_vector,
        listOf(TransactionType.EXPENSE)
    ),

    RENT(
        "Rent",
        R.drawable.rent_vector,
        listOf(TransactionType.EXPENSE)
    ),

    GIFTS(
        "Gifts",
        R.drawable.presents_vector,
        listOf(
            TransactionType.EXPENSE,
            TransactionType.INCOME
        )
    ),

    SAVINGS(
        "Savings",
        R.drawable.savings_vector,
        listOf(TransactionType.INCOME)
    ),

    ENTERTAINMENT(
        "Entertainment",
        R.drawable.entertainment_vector,
        listOf(TransactionType.EXPENSE)
    ),

    SALARY(
        "Salary",
        R.drawable.salary_vector,
        listOf(TransactionType.INCOME)
    ),

    MORE(
        "More",
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