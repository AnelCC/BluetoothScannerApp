package com.anelcc.bluetoothscanner.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScanningStatus (isScanning: Boolean, deviceCount: Int) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = if (isScanning) "Scanning for devices..." else "Found $deviceCount devices",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}