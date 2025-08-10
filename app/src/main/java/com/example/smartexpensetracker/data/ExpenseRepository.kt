package com.example.smartexpensetracker.data

import com.example.smartexpensetracker.model.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository class for managing expense-related data operations.
 * Acts as a mediator between the `ExpenseDao` and the rest of the application,
 * providing a clean API for accessing and manipulating expense data.
 *
 * @property dao The `ExpenseDao` instance used for database operations.
 */
class ExpenseRepository(private val dao: ExpenseDao) {

    /**
     * Adds a new expense to the database.
     * This operation is performed on the IO dispatcher to avoid blocking the main thread.
     *
     * @param expense The `Expense` object to be added to the database.
     */
    suspend fun add(expense: Expense) = withContext(Dispatchers.IO) { dao.insert(expense) }

    /**
     * Retrieves all expenses from the database.
     * This operation is performed on the IO dispatcher to avoid blocking the main thread.
     *
     * @return A list of all `Expense` objects in the database.
     */
    suspend fun all() = withContext(Dispatchers.IO) { dao.getAll() }

    /**
     * Retrieves expenses from the database that match the specified date.
     * This operation is performed on the IO dispatcher to avoid blocking the main thread.
     *
     * @param date The date to filter expenses by (in string format).
     * @return A list of `Expense` objects that match the specified date.
     */
    suspend fun byDate(date: String) = withContext(Dispatchers.IO) { dao.getByDate(date) }

    /**
     * Retrieves expenses from the database that occurred on or after the specified date.
     * This operation is performed on the IO dispatcher to avoid blocking the main thread.
     *
     * @param fromDate The starting date to filter expenses by (in string format).
     * @return A list of `Expense` objects that occurred on or after the specified date.
     */
    suspend fun sinceDate(fromDate: String) = withContext(Dispatchers.IO) { dao.getSinceDate(fromDate) }
}