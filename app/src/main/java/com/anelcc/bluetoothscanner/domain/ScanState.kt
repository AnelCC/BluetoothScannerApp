package com.anelcc.bluetoothscanner.domain

import com.anelcc.bluetoothscanner.data.BluetoothDeviceEntity

data class ScanState(
    val isScanning: Boolean = false,
    val devices: List<BluetoothDeviceEntity> = emptyList(),
    val error: String? = null
)