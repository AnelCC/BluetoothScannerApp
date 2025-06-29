package com.anelcc.bluetoothscanner.presentation

import android.bluetooth.BluetoothManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anelcc.bluetoothscanner.data.BluetoothRepositoryImpl
import com.anelcc.bluetoothscanner.domain.ObserveBluetoothDevicesUseCase
import com.anelcc.bluetoothscanner.domain.ObserveScanStateUseCase
import com.anelcc.bluetoothscanner.domain.StartBluetoothScanUseCase
import com.anelcc.bluetoothscanner.domain.StopBluetoothScanUseCase
import com.anelcc.bluetoothscanner.presentation.theme.BluetoothScannerTheme
import com.anelcc.bluetoothscanner.presentation.ui.BluetoothScannerScreen
import com.anelcc.bluetoothscanner.presentation.ui.BluetoothScannerViewModel

class MainActivity() : ComponentActivity() {
    private lateinit var viewModel: BluetoothScannerViewModel
    private lateinit var bluetoothRepository: BluetoothRepositoryImpl

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
        initializeDependencies()

        //Ask anc validate run time permissions
        checkAndRequestPermissions()

        setContent {
            BluetoothScannerTheme {
                // Here pass the initialized ViewModel to the Composable
                BluetoothScannerApp(viewModel)
            }
        }
    }

    private fun checkAndRequestPermissions() {
        val permissions = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            arrayOf(
                android.Manifest.permission.BLUETOOTH_SCAN,
                android.Manifest.permission.BLUETOOTH_CONNECT,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            arrayOf(
                android.Manifest.permission.BLUETOOTH,
                android.Manifest.permission.BLUETOOTH_ADMIN,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        }

        requestPermissionLauncher.launch(permissions)
    }

    private fun initializeDependencies() {
        val bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothRepository = BluetoothRepositoryImpl(this, bluetoothManager)

        viewModel = BluetoothScannerViewModel(
            startScanUseCase = StartBluetoothScanUseCase(bluetoothRepository),
            stopScanUseCase = StopBluetoothScanUseCase(bluetoothRepository),
            observeDevicesUseCase = ObserveBluetoothDevicesUseCase(bluetoothRepository),
            observeScanStateUseCase = ObserveScanStateUseCase(bluetoothRepository)
        )
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