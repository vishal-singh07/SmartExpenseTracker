package com.example.smartexpensetracker

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.*
import com.example.smartexpensetracker.ui.*
import com.example.smartexpensetracker.viewmodel.ExpenseViewModel

/**
 * Composable function for setting up the navigation graph of the application.
 * Includes a top app bar, bottom navigation bar, and navigation destinations.
 *
 * @param viewModel The `ExpenseViewModel` instance used to manage expense data.
 * @param darkTheme A Boolean indicating whether the dark theme is enabled.
 * @param onThemeToggle A callback function triggered when the theme toggle is switched.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    viewModel: ExpenseViewModel,
    darkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    // Remember the navigation controller for managing navigation between screens.
    val navController = rememberNavController()

    // Define the list of bottom navigation items.
    val bottomNavItems = listOf(NavItem.Entry, NavItem.List, NavItem.Report, NavItem.Setting)

    // Scaffold layout with a top app bar and bottom navigation bar.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart Expense Tracker") }
            )
        },
        bottomBar = {
            NavigationBar {
                // Get the current route from the navigation controller.
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(painter = painterResource(item.icon!!), contentDescription = null) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Define the navigation host with the start destination and navigation routes.
        NavHost(
            navController = navController,
            startDestination = NavItem.Entry.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavItem.Entry.route) {
                ExpenseEntryScreen(viewModel) {
                    navController.navigate(NavItem.List.route) {
                        popUpTo(NavItem.Entry.route) { inclusive = true }
                    }
                }
            }
            composable(NavItem.List.route) {
                ExpenseListScreen(viewModel)
            }
            composable(NavItem.Report.route) {
                ExpenseReportScreen(viewModel)
            }
            composable(NavItem.Setting.route) {
                SettingsScreen(
                    darkModeEnabled = darkTheme,
                    onDarkModeToggle = onThemeToggle
                )
            }
        }
    }
}