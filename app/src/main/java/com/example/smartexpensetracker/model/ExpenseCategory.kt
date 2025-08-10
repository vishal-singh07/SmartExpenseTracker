package com.example.smartexpensetracker.model

/**
 * Enum class representing different categories of expenses.
 * Each category is associated with a string value for easy identification.
 *
 * @property value The string representation of the expense category.
 */
enum class ExpenseCategory(val value: String) {
    Staff("Staff"),       // Represents expenses related to staff.
    Travel("Travel"),     // Represents expenses related to travel.
    Food("Food"),         // Represents expenses related to food.
    Utility("Utility");   // Represents expenses related to utilities.

    companion object {
        /**
         * Retrieves an `ExpenseCategory` instance based on its string value.
         *
         * @param value The string value of the desired expense category.
         * @return The corresponding `ExpenseCategory` instance.
         * @throws NoSuchElementException if no matching category is found.
         */
        fun fromValue(value: String) = entries.first { it.value == value }
    }
}