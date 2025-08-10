package com.example.smartexpensetracker.ui

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.smartexpensetracker.viewmodel.ExpenseViewModel
import com.example.smartexpensetracker.model.Expense
import com.example.smartexpensetracker.model.ExpenseCategory

import java.time.LocalDate
import java.util.UUID

/**
 * Composable function for the Expense Entry Screen.
 * Allows users to input expense details and submit them to the database.
 *
 * @param viewModel The `ExpenseViewModel` instance used to manage expense data.
 * @param onExpenseAdded Callback function triggered after a new expense is successfully added.
 */
@Composable
fun ExpenseEntryScreen(viewModel: ExpenseViewModel, onExpenseAdded: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(ExpenseCategory.Staff) }
    var notes by remember { mutableStateOf("") }
    val today = LocalDate.now().toString()
    val expenseList = viewModel.expenses.collectAsState(emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        val todayTotal = expenseList.value.filter { it.date == today }.sumOf { it.amount }
        Text("Total Spent Today: ₹$todayTotal", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") }, modifier = Modifier.fillMaxWidth())
        TextField(
            value = amount, onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenuCategory(category) { category = it }
        TextField(
            value = notes, onValueChange = { notes = it.take(100) },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                val amt = amount.toDoubleOrNull() ?: 0.0
                if (title.isNotBlank() && amt > 0.0) {
                    val expense = Expense(
                        id = UUID.randomUUID().toString(),
                        title = title,
                        amount = amt,
                        category = category.value,
                        notes = notes,
                        date = today,
                        imageUri = null
                    )
                    viewModel.addExpense(expense)
                    title = ""; amount = ""; notes = ""
                    onExpenseAdded()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Submit") }
    }
}

/**
 * Composable function for the category dropdown menu.
 * Allows users to select an expense category from a predefined list.
 *
 * @param selected The currently selected `ExpenseCategory`.
 * @param onCategoryChange Callback function triggered when the user selects a new category.
 */
@Composable
fun DropdownMenuCategory(selected: ExpenseCategory, onCategoryChange: (ExpenseCategory) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
            Text(selected.value)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            ExpenseCategory.entries.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.value) },
                    onClick = {
                        onCategoryChange(category)
                        expanded = false
                    }
                )
            }
        }
    }
}