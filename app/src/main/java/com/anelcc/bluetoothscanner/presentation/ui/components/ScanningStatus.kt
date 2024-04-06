package com.anelcc.bluetoothscanner.presentation.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ScanningStatus (isScanning: Boolean, deviceCount: Int) {
    Text(
        text = if (isScanning) "Scanning for devices..." else "Found $deviceCount devices",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}