package com.anelcc.bluetoothscanner.core

interface BluetoothRepository {
    suspend fun startScan(): Result<Unit>
    suspend fun stopScan(): Result<Unit>
    suspend fun getDiscoveredDevices(): List<Any>
    suspend fun observeDevices(): kotlinx.coroutines.flow.Flow<List<Any>>
    suspend fun observeScanState(): kotlinx.coroutines.flow.Flow<Boolean>
    suspend fun hasBluetoothPermissions(): Boolean
    suspend fun isBluetoothEnabled(): Boolean
}