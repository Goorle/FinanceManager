package com.example.financemanager.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.financemanager.presentation.components.BottomBar
import com.example.financemanager.presentation.components.topbars.TopBarHome
import com.example.financemanager.presentation.components.bottomNavItem
import com.example.financemanager.presentation.components.topbars.DefaultTopBar
import com.example.financemanager.ui.theme.CaribbeanGreen
import com.example.financemanager.ui.theme.HoneyDew

@Composable
fun AppScaffold() {
    val navHostController = rememberNavController()
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val items = bottomNavItem()
    val currentRoute: RoutesScreen? = items.firstOrNull { item ->
        backStackEntry?.destination?.hasRoute(item.route::class) == true
    }?.route


    Scaffold(
        containerColor = CaribbeanGreen,
        topBar = {
            when(currentRoute) {
                RoutesScreen.Home -> TopBarHome()
                null -> {
                    DefaultTopBar(
                        title = "Finance Manager",
                        onClickBack = {},
                    ) { }
                }
                else -> {
                    DefaultTopBar(
                        title = currentRoute.route,
                        onClickBack = {
                            navHostController.popBackStack()
                        }
                    ) { }
                }
            }

        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HoneyDew)
            ){
                BottomBar(
                    selectedRoute = currentRoute ?: RoutesScreen.Home,
                    items = items,
                    onItemClick = { route ->
                        navHostController.navigate(route) {
                            popUpTo(navHostController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavGraph(appNavController = navHostController)
        }

    }
}