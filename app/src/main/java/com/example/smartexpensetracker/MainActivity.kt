package com.example.smartexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.room.Room
import com.example.smartexpensetracker.data.ExpenseDatabase
import com.example.smartexpensetracker.data.ExpenseRepository
import com.example.smartexpensetracker.ui.theme.SmartExpenseTrackerTheme
import com.example.smartexpensetracker.viewmodel.ExpenseViewModel

/**
 * Main activity for the Smart Expense Tracker application.
 * Initializes the database, repository, and ViewModel, and sets up the Compose UI.
 */
class MainActivity : ComponentActivity() {
    // Instance of the ExpenseViewModel to manage expense data.
    private lateinit var viewModel: ExpenseViewModel

    /**
     * Called when the activity is starting. Sets up the database, repository, ViewModel,
     * and the Compose UI with a theme and navigation graph.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the data it most recently supplied in onSaveInstanceState. Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the Room database.
        val db = Room.databaseBuilder(
            applicationContext,
            ExpenseDatabase::class.java, "expense-db"
        ).build()

        // Create an instance of the repository using the DAO from the database.
        val repo = ExpenseRepository(db.expenseDao())

        // Initialize the ViewModel with the repository.
        viewModel = ExpenseViewModel(repo)

        // Set the Compose content for the activity.
        setContent {
            // State to manage the theme (dark or light).
            var darkTheme by remember { mutableStateOf(false) }

            // Apply the theme and set up the navigation graph.
            SmartExpenseTrackerTheme(darkTheme = darkTheme) {
                NavGraph(
                    viewModel = viewModel,
                    darkTheme = darkTheme,
                    onThemeToggle = { darkTheme = it }
                )
            }
        }
    }
}