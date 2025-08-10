package com.example.smartexpensetracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.smartexpensetracker.model.Expense

/**
 * The main database class for the application.
 * This class defines the database configuration and serves as the main access point
 * for the underlying SQLite database.
 *
 * @property expenseDao Provides access to the `ExpenseDao` for performing database operations.
 */
@Database(entities = [Expense::class], version = 1)
abstract class ExpenseDatabase : RoomDatabase() {

    /**
     * Abstract method to get the `ExpenseDao` instance.
     * This method is used to access the DAO for performing CRUD operations on the `Expense` entity.
     *
     * @return An instance of `ExpenseDao`.
     */
    abstract fun expenseDao(): ExpenseDao
}