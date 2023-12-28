package com.anelcc.bluetoothscanner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anelcc.bluetoothscanner.presentation.theme.BluetoothScannerTheme
import com.anelcc.bluetoothscanner.presentation.ui.BluetoothScannerScreen
import com.anelcc.bluetoothscanner.presentation.ui.BluetoothScannerViewModel

class MainActivity() : ComponentActivity() {
    private val viewModel: BluetoothScannerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BluetoothScannerTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                BluetoothScannerScreen(
                    state = uiState,
                    onStartScan = viewModel::startScan,
                    onStopScan = viewModel::stopScan,
                    onClearError = viewModel::clearError
                )
            }
        }
    }
}