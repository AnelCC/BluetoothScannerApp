package com.anelcc.bluetoothscanner.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class ObserveBluetoothDevicesUseCase(private val repository: Any) {
    suspend operator fun invoke(): Flow<List<Any>> {
//        return repository.observeDevices()
        return emptyFlow()
    }
}