package com.example.financemanager.presentation.navigation

import com.example.financemanager.domain.model.Category
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
sealed class RoutesScreen(@Transient val  route: String = "") {

    @Serializable
    object Home: RoutesScreen("Home")
    @Serializable
    object Categories: RoutesScreen("Categories")
    @Serializable data class CategoryDetails(val category: Category): RoutesScreen("Category")
    @Serializable
    object AddExtension: RoutesScreen("Add Extension")
    @Serializable
    object Analysis: RoutesScreen("Analysis")
    @Serializable
    object Transaction: RoutesScreen("Transaction")
    @Serializable
    object Profile: RoutesScreen("Profile")
}