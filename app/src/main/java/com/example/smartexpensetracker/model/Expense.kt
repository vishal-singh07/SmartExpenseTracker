package com.example.smartexpensetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents an expense entity in the database.
 * This data class is used to define the structure of the `expenses` table in the Room database.
 *
 * @property id The unique identifier for the expense. Acts as the primary key.
 * @property title The title or description of the expense.
 * @property amount The monetary value of the expense.
 * @property category The category of the expense (e.g., Food, Travel). Stored as a String.
 * @property notes Optional notes or additional details about the expense.
 * @property date The date of the expense in ISO-8601 format (yyyy-MM-dd).
 * @property imageUri Optional URI of an image associated with the expense (e.g., receipt).
 */
@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey val id: String,
    val title: String,
    val amount: Double,
    val category: String,        // Stored as String for Room
    val notes: String?,
    val date: String,            // ISO-8601 string (yyyy-MM-dd)
    val imageUri: String?
)