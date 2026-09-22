package com.example.financemanager.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.financemanager.domain.model.Category
import com.example.financemanager.presentation.categories.CategoriesScreen
import com.example.financemanager.presentation.categories.CategoryDetails
import com.example.financemanager.presentation.home.HomeScreen

@Composable
fun AppNavGraph(
    appNavController: NavHostController
) {
    NavHost(navController = appNavController,
            startDestination = RoutesScreen.Home
    ) {
        composable<RoutesScreen.Home> {
            HomeScreen()
        }

        composable<RoutesScreen.Categories> {
            CategoriesScreen(
                onClickCategory = { category ->
                    appNavController.navigate(RoutesScreen.CategoryDetails(category))
                }
            )
        }
        composable<RoutesScreen.CategoryDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<RoutesScreen.CategoryDetails>()
            CategoryDetails(args.category)
        }
        composable<RoutesScreen.Profile> {  }
        composable<RoutesScreen.Analysis>{}
        composable<RoutesScreen.Transaction>{}
    }
}