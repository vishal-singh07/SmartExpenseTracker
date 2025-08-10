package com.example.smartexpensetracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartexpensetracker.model.Expense

/**
 * Data Access Object (DAO) for managing database operations related to the `Expense` entity.
 * Provides methods to insert and query expense data from the database.
 */
@Dao
interface ExpenseDao {

    /**
     * Inserts an expense into the database.
     * If an expense with the same primary key already exists, it will be replaced.
     *
     * @param expense The `Expense` object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    /**
     * Retrieves all expenses from the database, ordered by date in descending order.
     *
     * @return A list of all `Expense` objects, sorted by date (newest first).
     */
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    suspend fun getAll(): List<Expense>

    /**
     * Retrieves all expenses from the database that match the specified date.
     *
     * @param date The date to filter expenses by (in string format).
     * @return A list of `Expense` objects that match the specified date.
     */
    @Query("SELECT * FROM expenses WHERE date = :date")
    suspend fun getByDate(date: String): List<Expense>

    /**
     * Retrieves all expenses from the database that occurred on or after the specified date.
     *
     * @param fromDate The starting date to filter expenses by (in string format).
     * @return A list of `Expense` objects that occurred on or after the specified date.
     */
    @Query("SELECT * FROM expenses WHERE date >= :fromDate")
    suspend fun getSinceDate(fromDate: String): List<Expense>
}