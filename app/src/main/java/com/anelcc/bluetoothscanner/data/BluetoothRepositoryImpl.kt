package com.anelcc.bluetoothscanner.data

import android.Manifest
import android.Manifest.permission.BLUETOOTH
import android.Manifest.permission.BLUETOOTH_ADMIN
import android.Manifest.permission.BLUETOOTH_CONNECT
import android.Manifest.permission.BLUETOOTH_SCAN
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import androidx.core.content.ContextCompat
import com.anelcc.bluetoothscanner.core.BluetoothRepository
import kotlinx.coroutines.flow.Flow

class BluetoothRepositoryImpl(
    private val context: Context,
    private val bluetoothManager: BluetoothManager
) : BluetoothRepository {

    private val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter

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
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ContextCompat.checkSelfPermission(context, BLUETOOTH_SCAN) == PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, BLUETOOTH_CONNECT) == PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(context, BLUETOOTH) == PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, BLUETOOTH_ADMIN) == PERMISSION_GRANTED
        }
    }

    override suspend fun isBluetoothEnabled(): Boolean {
        return bluetoothAdapter.isEnabled
    }

}