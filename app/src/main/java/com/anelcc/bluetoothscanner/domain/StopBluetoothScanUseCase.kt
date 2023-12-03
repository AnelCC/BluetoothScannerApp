package com.anelcc.bluetoothscanner.domain

class StopBluetoothScanUseCase(private val repository: Any) {
    suspend operator fun invoke(): Result<Unit> {
//        return repository.stopScan()
        return Result.success(Unit)
    }
}
