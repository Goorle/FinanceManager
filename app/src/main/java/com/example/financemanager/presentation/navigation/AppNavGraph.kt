package com.example.financemanager.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.financemanager.domain.model.Category
import com.example.financemanager.presentation.addExpenses.AddExpenseScreen
import com.example.financemanager.presentation.categories.CategoriesScreen
import com.example.financemanager.presentation.categories.CategoryDetails
import com.example.financemanager.presentation.home.HomeScreen

@Composable
fun AppNavGraph(
    appNavController: NavHostController,
    snackbarHostState: SnackbarHostState
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
        composable<RoutesScreen.CategoryDetails> {
            CategoryDetails(
                onClickAddExpense = { category ->
                    appNavController.navigate(RoutesScreen.AddExpense(category))
                }
            )
        }

        composable<RoutesScreen.AddExpense> {
            AddExpenseScreen(
                onExpenseSaved = {
                    appNavController.popBackStack()
                },
                snackbarHostState = snackbarHostState
            )
        }

        composable<RoutesScreen.Profile> {  }
        composable<RoutesScreen.Analysis>{}
        composable<RoutesScreen.Transaction>{}
    }
}