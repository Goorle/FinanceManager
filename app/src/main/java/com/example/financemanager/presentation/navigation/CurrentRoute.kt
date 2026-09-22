package com.example.financemanager.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.toRoute

// Все "простые" маршруты без аргументов
private val simpleRoutes: List<RoutesScreen> = listOf(
    RoutesScreen.Home,
    RoutesScreen.Categories,
    RoutesScreen.Analysis,
    RoutesScreen.Transaction,
    RoutesScreen.Profile
)

@Composable
fun currentRoute(backStackEntry: NavBackStackEntry?): RoutesScreen? {
    val destination = backStackEntry?.destination ?: return null

    if (destination.hierarchy.any { it.hasRoute(RoutesScreen.CategoryDetails::class)}) {
        return backStackEntry.toRoute<RoutesScreen.CategoryDetails>()
    }

    return simpleRoutes.firstOrNull{route ->
        destination.hierarchy.any{it.hasRoute(route::class)}
    }
}