package com.anelcc.bluetoothscanner.presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anelcc.bluetoothscanner.presentation.theme.BluetoothScannerTheme

@Composable
fun ScanButton(
    isScanning: Boolean = true,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit
) {
    if (isScanning) {
        Button(
            onClick = onStopScan,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                color = MaterialTheme.colorScheme.onError,
                strokeWidth = 2.dp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Stop Scanning")
        }
    } else {
        Button(onClick = onStartScan) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Scan"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Start Scan")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScanButtonPreview() {
    BluetoothScannerTheme {
        ScanButton(
            isScanning = true,
            onStartScan = {},
            onStopScan = {}
        )
    }
}