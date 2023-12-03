package com.anelcc.bluetoothscanner.presentation.ui

import androidx.lifecycle.ViewModel
import com.anelcc.bluetoothscanner.domain.ScanState
import com.anelcc.bluetoothscanner.domain.StartBluetoothScanUseCase
import com.anelcc.bluetoothscanner.domain.StopBluetoothScanUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BluetoothScannerViewModel(
    private val startScanUseCase: StartBluetoothScanUseCase,
    private val stopScanUseCase: StopBluetoothScanUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScanState())
    val uiState: StateFlow<ScanState> = _uiState.asStateFlow()


    init {
       // fetch Bluetooth data
    }

    private fun observeBluetoothData() {
        // Observe Bluetooth data and update the UI state
    }

    fun startScan() {
        // Handle start scan
    }
    fun stopScan() {
        // Handle stop scan
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

