package com.anelcc.bluetoothscanner.data

import android.bluetooth.BluetoothManager
import android.content.Context
import com.anelcc.bluetoothscanner.core.BluetoothRepository
import kotlinx.coroutines.flow.Flow

class BluetoothRepositoryImpl(
    private val context: Context,
    private val bluetoothManager: BluetoothManager
) : BluetoothRepository {
    override suspend fun startScan(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun stopScan(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getDiscoveredDevices(): List<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun observeDevices(): Flow<List<Any>> {
        TODO("Not yet implemented")
    }

    override suspend fun observeScanState(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun hasBluetoothPermissions(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun isBluetoothEnabled(): Boolean {
        TODO("Not yet implemented")
    }

}