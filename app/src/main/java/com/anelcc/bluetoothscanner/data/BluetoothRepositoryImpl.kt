package com.anelcc.bluetoothscanner.data

import android.Manifest.permission.BLUETOOTH
import android.Manifest.permission.BLUETOOTH_ADMIN
import android.Manifest.permission.BLUETOOTH_CONNECT
import android.Manifest.permission.BLUETOOTH_SCAN
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import androidx.core.content.ContextCompat
import com.anelcc.bluetoothscanner.core.BluetoothRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BluetoothRepositoryImpl(
    private val context: Context,
    private val bluetoothManager: BluetoothManager
) : BluetoothRepository {

    private val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter

    private val _devices = MutableStateFlow<List<BluetoothDeviceEntity>>(emptyList())
    private val _isScanning = MutableStateFlow(false)

    override suspend fun startScan(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun stopScan(): Result<Unit> {
        TODO("Not yet implemented")
    }

    //if we find devices return the list
    override suspend fun getDiscoveredDevices(): List<BluetoothDeviceEntity> {
        return _devices.value
    }

    //if we have devices observe and return sync data
    override suspend fun observeDevices(): Flow<List<BluetoothDeviceEntity>> {
        return _devices
    }

    //validate is scanning
    override suspend fun observeScanState(): Flow<Boolean> {
        return _isScanning
    }

    //validate permission
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