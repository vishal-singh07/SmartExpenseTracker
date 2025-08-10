package com.example.smartexpensetracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.smartexpensetracker.data.ExpenseRepository
import com.example.smartexpensetracker.model.Expense
import kotlinx.coroutines.launch

/**
 * ViewModel class for managing expense data.
 * Acts as a bridge between the UI and the data layer, providing a reactive data flow.
 *
 * @property repo The `ExpenseRepository` instance used for accessing expense data.
 */
class ExpenseViewModel(private val repo: ExpenseRepository) : ViewModel() {

    // A private mutable state flow holding the list of expenses.
    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())

    /**
     * A public immutable state flow exposing the list of expenses.
     * Observers can collect this flow to react to changes in the expense list.
     */
    val expenses: StateFlow<List<Expense>> = _expenses

    /**
     * Refreshes the list of expenses by fetching all expenses from the repository.
     * Updates the `_expenses` state flow with the latest data.
     */
    fun refresh() = viewModelScope.launch { _expenses.value = repo.all() }

    /**
     * Adds a new expense to the repository and refreshes the expense list.
     *
     * @param expense The `Expense` object to be added.
     */
    fun addExpense(expense: Expense) = viewModelScope.launch {
        repo.add(expense)
        refresh()
    }

    /**
     * Fetches expenses for a specific date and updates the `_expenses` state flow.
     *
     * @param date The date (in String format) for which expenses are to be fetched.
     */
    fun expensesByDate(date: String) = viewModelScope.launch { _expenses.value = repo.byDate(date) }

    /**
     * Fetches expenses from the last 7 days (or since a specific date) and updates the `_expenses` state flow.
     *
     * @param since The starting date (in String format) from which expenses are to be fetched.
     */
    fun expensesLast7Days(since: String) = viewModelScope.launch { _expenses.value = repo.sinceDate(since) }
}