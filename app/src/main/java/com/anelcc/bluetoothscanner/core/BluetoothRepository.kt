package com.anelcc.bluetoothscanner.core

import com.anelcc.bluetoothscanner.data.BluetoothDeviceEntity
import kotlinx.coroutines.flow.Flow

interface BluetoothRepository {
    suspend fun startScan(): Result<Unit>
    suspend fun stopScan(): Result<Unit>
    suspend fun getDiscoveredDevices(): List<BluetoothDeviceEntity>
    suspend fun observeDevices(): Flow<List<BluetoothDeviceEntity>>
    suspend fun observeScanState(): Flow<Boolean>
    suspend fun hasBluetoothPermissions(): Boolean
    suspend fun isBluetoothEnabled(): Boolean
}