package com.anelcc.bluetoothscanner.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BluetoothScannerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header Card
        Spacer(modifier = Modifier.height(16.dp))
        // Error Display
        // Status Text
        Spacer(modifier = Modifier.height(8.dp))
        // Device List
        DeviceList()
    }
}