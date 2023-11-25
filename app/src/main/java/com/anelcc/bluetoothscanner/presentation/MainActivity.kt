package com.anelcc.bluetoothscanner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.anelcc.bluetoothscanner.domain.ScanState
import com.anelcc.bluetoothscanner.presentation.theme.BluetoothScannerTheme
import com.anelcc.bluetoothscanner.presentation.ui.BluetoothScannerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BluetoothScannerTheme {
                BluetoothScannerScreen(
                    state = ScanState(),
                    onStartScan = { /* Handle start scan */ },
                    onStopScan = { /* Handle stop scan */ },
                    onClearError = { /* Handle clear error */ }
                )
            }
        }
    }
}