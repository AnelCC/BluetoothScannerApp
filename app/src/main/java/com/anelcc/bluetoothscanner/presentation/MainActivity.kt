package com.anelcc.bluetoothscanner.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anelcc.bluetoothscanner.presentation.theme.BluetoothScannerTheme
import com.anelcc.bluetoothscanner.presentation.ui.BluetoothScannerScreen
import com.anelcc.bluetoothscanner.presentation.ui.BluetoothScannerViewModel

class MainActivity() : ComponentActivity() {
    private lateinit var viewModel: BluetoothScannerViewModel

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (!allGranted) {
            Toast.makeText(this, "Bluetooth permissions are required", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Here Initialize the ViewModel with dependencies


        setContent {
            BluetoothScannerTheme {
                // Here pass the initialized ViewModel to the Composable
                BluetoothScannerApp(viewModel)
            }
        }
    }
}


@Composable
fun BluetoothScannerApp(viewModel: BluetoothScannerViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BluetoothScannerScreen(
        state = uiState,
        onStartScan = viewModel::startScan,
        onStopScan = viewModel::stopScan,
        onClearError = viewModel::clearError
    )
}