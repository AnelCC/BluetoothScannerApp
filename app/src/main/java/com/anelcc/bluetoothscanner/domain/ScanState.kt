package com.anelcc.bluetoothscanner.domain

data class ScanState(
    val isScanning: Boolean = false,
    val devices: List<Any> = emptyList(),
    val error: String? = null
)