package com.anelcc.bluetoothscanner.domain

import com.anelcc.bluetoothscanner.core.BluetoothRepository
import kotlinx.coroutines.flow.Flow

class ObserveScanStateUseCase(private val repository: BluetoothRepository) {
    suspend operator fun invoke(): Flow<Boolean> {
        return repository.observeScanState()
    }
}