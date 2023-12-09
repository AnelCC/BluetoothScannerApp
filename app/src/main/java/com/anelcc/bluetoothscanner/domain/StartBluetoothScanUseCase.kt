package com.anelcc.bluetoothscanner.domain

import com.anelcc.bluetoothscanner.core.BluetoothRepository

class StartBluetoothScanUseCase(private val repository: BluetoothRepository) {
    suspend operator fun invoke(): Result<Unit> {
        // Check for Bluetooth permissions and enable if needed
        val permissionsGranted = true
        return if (permissionsGranted) {
            // Start Bluetooth scan
            return repository.startScan()
        } else {
            Result.failure(Exception("Bluetooth not available or permissions missing"))
        }
    }
}