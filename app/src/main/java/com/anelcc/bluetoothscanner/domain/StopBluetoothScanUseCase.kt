package com.anelcc.bluetoothscanner.domain

import com.anelcc.bluetoothscanner.core.BluetoothRepository

class StopBluetoothScanUseCase(private val repository: BluetoothRepository) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.stopScan()
    }
}
