package com.anelcc.bluetoothscanner.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anelcc.bluetoothscanner.domain.ObserveBluetoothDevicesUseCase
import com.anelcc.bluetoothscanner.domain.ObserveScanStateUseCase
import com.anelcc.bluetoothscanner.domain.ScanState
import com.anelcc.bluetoothscanner.domain.StartBluetoothScanUseCase
import com.anelcc.bluetoothscanner.domain.StopBluetoothScanUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class BluetoothScannerViewModel(
    private val stopScanUseCase: StopBluetoothScanUseCase,
    private val startScanUseCase: StartBluetoothScanUseCase,
    private val observeScanStateUseCase: ObserveScanStateUseCase,
    private val observeDevicesUseCase: ObserveBluetoothDevicesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScanState())
    val uiState: StateFlow<ScanState> = _uiState.asStateFlow()


    init {
       // fetch Bluetooth data
        observeBluetoothData()
    }

    // Observe Bluetooth data and update the UI state
    private fun observeBluetoothData() {
        viewModelScope.launch {
            val devicesFlow = observeDevicesUseCase()
            val scanStateFlow = observeScanStateUseCase()

            combine(devicesFlow, scanStateFlow) { devices, isScanning ->
                ScanState(isScanning = isScanning, devices = devices)
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    // Handle start scan
    fun startScan() {
        viewModelScope.launch {
            startScanUseCase().fold(
                onSuccess = {
                    _uiState.value = _uiState.value.copy(error = null)
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(error = error.message)
                }
            )
        }
    }

    // Handle stop scan
    fun stopScan() {
        viewModelScope.launch {
            stopScanUseCase().fold(
                onSuccess = {
                    _uiState.value = _uiState.value.copy(error = null)
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(error = error.message)
                }
            )
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

