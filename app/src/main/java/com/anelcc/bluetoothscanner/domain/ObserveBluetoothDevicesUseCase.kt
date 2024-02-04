package com.anelcc.bluetoothscanner.domain

import com.anelcc.bluetoothscanner.core.BluetoothRepository
import com.anelcc.bluetoothscanner.data.BluetoothDeviceEntity
import kotlinx.coroutines.flow.Flow

class ObserveBluetoothDevicesUseCase(private val repository: BluetoothRepository) {
    suspend operator fun invoke(): Flow<List<BluetoothDeviceEntity>> {
        return repository.observeDevices()
    }
}