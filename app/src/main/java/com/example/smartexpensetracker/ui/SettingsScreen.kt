package com.example.smartexpensetracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable function for the Settings Screen.
 * Provides a user interface for toggling application settings, such as Dark Mode.
 *
 * @param darkModeEnabled A Boolean indicating whether Dark Mode is currently enabled.
 * @param onDarkModeToggle A callback function triggered when the Dark Mode toggle is switched.
 */
@Composable
fun SettingsScreen(
    darkModeEnabled: Boolean,
    onDarkModeToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Dark Mode",
                style = MaterialTheme.typography.bodyLarge
            )
            Switch(
                checked = darkModeEnabled,
                onCheckedChange = { onDarkModeToggle(it) }
            )
        }
    }
}