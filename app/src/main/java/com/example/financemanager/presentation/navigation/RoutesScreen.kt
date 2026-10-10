package com.example.financemanager.presentation.navigation

import androidx.annotation.StringRes
import com.example.financemanager.R
import com.example.financemanager.domain.model.Category
import kotlinx.serialization.Serializable

@Serializable
sealed class RoutesScreen {
    @Serializable object Home : RoutesScreen()
    @Serializable object Categories : RoutesScreen()
    @Serializable data class CategoryDetails(val category: Category) : RoutesScreen()
    @Serializable data class AddExpense(val category: Category) : RoutesScreen()
    @Serializable object Analysis : RoutesScreen()
    @Serializable object Transaction : RoutesScreen()
    @Serializable object Profile : RoutesScreen()
}

@StringRes
fun RoutesScreen.titleRes(): Int = when (this) {
    RoutesScreen.Home -> R.string.title_home
    RoutesScreen.Categories -> R.string.title_categories
    is RoutesScreen.CategoryDetails -> R.string.category
    is RoutesScreen.AddExpense -> R.string.title_add_expense
    RoutesScreen.Analysis -> R.string.title_analysis
    RoutesScreen.Transaction -> R.string.title_transaction
    RoutesScreen.Profile -> R.string.title_profile
}