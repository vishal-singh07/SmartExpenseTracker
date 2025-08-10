package com.example.smartexpensetracker.ui

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartexpensetracker.model.Expense
import com.example.smartexpensetracker.viewmodel.ExpenseViewModel
import java.time.LocalDate

/**
 * Composable function for displaying a list of expenses for a specific date.
 * Fetches and displays the total amount, count, and individual expense details.
 *
 * @param viewModel The `ExpenseViewModel` instance used to manage expense data.
 * @param selectedDate The date for which expenses are displayed. Defaults to the current date.
 */
@Composable
fun ExpenseListScreen(viewModel: ExpenseViewModel, selectedDate: LocalDate = LocalDate.now()) {
    val date = selectedDate.toString()
    val expenses = viewModel.expenses.collectAsState(emptyList())
    LaunchedEffect(date) { viewModel.expensesByDate(date) }
    val list = expenses.value
    val total = list.sumOf { it.amount }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Expenses for $date", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("Total: ₹$total, Count: ${list.size}")
        Spacer(Modifier.height(8.dp))

        if (list.isEmpty()) {
            Text("No expenses recorded!", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn {
                items(list) { expense -> ExpenseRow(expense) }
            }
        }
    }
}

/**
 * Composable function for displaying a single expense item.
 * Shows the title, amount, category, notes (if available), and date of the expense.
 *
 * @param expense The `Expense` object containing the details of the expense.
 */
@Composable
fun ExpenseRow(expense: Expense) {
    Card(
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier.fillMaxWidth().padding(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(expense.title, style = MaterialTheme.typography.titleMedium)
                Text("₹${expense.amount} (${expense.category})", style = MaterialTheme.typography.bodyMedium)
                expense.notes?.let { Text("Notes: $it", style = MaterialTheme.typography.titleSmall) }
            }
            Text(expense.date, style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(start = 8.dp))
        }
    }
}