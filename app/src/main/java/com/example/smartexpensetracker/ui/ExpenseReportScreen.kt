package com.example.smartexpensetracker.ui

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import com.example.smartexpensetracker.viewmodel.ExpenseViewModel
import java.time.LocalDate
import kotlin.math.roundToInt

/**
 * Composable function for displaying a 7-day expense report.
 * Shows a bar chart of daily expenses, category totals, and an export button.
 *
 * @param viewModel The `ExpenseViewModel` instance used to fetch and manage expense data.
 */
@Composable
fun ExpenseReportScreen(viewModel: ExpenseViewModel) {
    val fromDate = LocalDate.now().minusDays(6).toString()
    val expensesState = viewModel.expenses.collectAsState(emptyList())
    LaunchedEffect(Unit) {
        viewModel.expensesLast7Days(fromDate)
    }
    val expenses = expensesState.value

    // Group expenses by date and sum amounts
    val dailyTotals = (0..6).map { offset ->
        val dateKey = LocalDate.now().minusDays((6 - offset).toLong()).toString()
        val total = expenses.filter { it.date == dateKey }.sumOf { it.amount }
        dateKey to total
    }

    // Determine the max value for bar height scaling
    val maxAmount = dailyTotals.maxOfOrNull { it.second } ?: 1.0

    Column(modifier = Modifier.padding(16.dp)) {
        Text("7-Day Expense Report", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        // Bar Chart Canvas
        BarChart(dailyTotals, maxAmount)

        Spacer(Modifier.height(16.dp))

        Text("Category Totals:")
        val categoryTotals = expenses.groupBy { it.category }
            .map { it.key to it.value.sumOf { e -> e.amount } }
        categoryTotals.forEach { (category, amount) ->
            Text("$category: ₹${amount.roundToInt()}")
        }

        Spacer(Modifier.height(24.dp))

        Button(onClick = { /* Simulate export functionality */ }) {
            Text("Export (Mock)")
        }
    }
}

/**
 * Composable function for rendering a bar chart.
 * Displays bars representing daily expense totals, scaled relative to the maximum amount.
 *
 * @param data A list of pairs where each pair contains a date (String) and the total expense (Double) for that date.
 * @param maxAmount The maximum expense amount used for scaling bar heights.
 * @param modifier The `Modifier` to be applied to the bar chart container.
 */
@Composable
fun BarChart(data: List<Pair<String, Double>>, maxAmount: Double, modifier: Modifier = Modifier) {
    val barWidth = 30.dp
    val barSpacing = 16.dp
    val chartHeight = 200.dp
    val barColor = MaterialTheme.colorScheme.primary
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {
        Row(
            modifier = Modifier
                .height(chartHeight)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(barSpacing)
        ) {
            data.forEach { (date, amount) ->
                val barHeightRatio = (amount / maxAmount).toFloat().coerceIn(0f, 1f)
                Box(
                    modifier = Modifier
                        .width(barWidth)
                        .fillMaxHeight(),
                    contentAlignment = androidx.compose.ui.Alignment.BottomCenter
                ) {
                    Canvas(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(barHeightRatio)
                    ) {
                        drawRect(
                            color = barColor,
                            style = Fill
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(barSpacing)
        ) {
            data.forEach { (date, _) ->
                BasicText(
                    text = LocalDate.parse(date).dayOfMonth.toString(),
                    modifier = Modifier.width(barWidth),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}