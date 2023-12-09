package com.anelcc.bluetoothscanner.domain

import com.anelcc.bluetoothscanner.core.BluetoothRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class ObserveBluetoothDevicesUseCase(private val repository: BluetoothRepository) {
    suspend operator fun invoke(): Flow<List<Any>> {
        return repository.observeDevices()
    }
}