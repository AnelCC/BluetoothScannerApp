package com.anelcc.bluetoothscanner.data

import android.Manifest.permission.BLUETOOTH
import android.Manifest.permission.BLUETOOTH_ADMIN
import android.Manifest.permission.BLUETOOTH_CONNECT
import android.Manifest.permission.BLUETOOTH_SCAN
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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

    private val _isScanning = MutableStateFlow(false)
    private val discoveredDevices = mutableListOf<BluetoothDeviceEntity>()
    private val _devices = MutableStateFlow<List<BluetoothDeviceEntity>>(emptyList())

    private val bluetoothReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {

                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)
                    } else {
                        @Suppress("DEPRECATION")
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    }

                    val rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE).toInt()
                    device?.let { bluetoothDevice ->
                        val deviceEntity = bluetoothDevice.toBluetoothDeviceEntity(rssi)
                        if (!discoveredDevices.any { it.address == deviceEntity.address }) {
                            discoveredDevices.add(deviceEntity)
                            _devices.value = discoveredDevices.toList()
                        }
                    }
                }

                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    _isScanning.value = false
                }
            }
        }
    }

    override suspend fun startScan(): Result<Unit> {
        return try {

            //Validate bluetooth before scanning
            if (!hasBluetoothPermissions()) {
                return Result.failure(Exception("Bluetooth permissions not granted"))
            }

            //Validate bluetooth before scanning
            if (!isBluetoothEnabled()) {
                return Result.failure(Exception("Bluetooth not enabled"))
            }

            // we clean the list of devices if we are scanning
            discoveredDevices.clear()
            _devices.value = emptyList()

            //cancel Discovery devices if still scanning
            if (bluetoothAdapter.isDiscovering) {
                bluetoothAdapter.cancelDiscovery()
            }

            _isScanning.value = true
            val started = bluetoothAdapter.startDiscovery()

            if (started) {
                Result.success(Unit)
            } else {
                _isScanning.value = false
                Result.failure(Exception("Failed to start discovery"))
            }

        } catch (e: SecurityException) {
            _isScanning.value = false
            Result.failure(e)
        }
    }

    override suspend fun stopScan(): Result<Unit> {
        return try {
            if (bluetoothAdapter.isDiscovering) {
                bluetoothAdapter.cancelDiscovery()
            }
            _isScanning.value = false
            Result.success(Unit)
        } catch (e: SecurityException) {
            Result.failure(e)
        }
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


// Extension function to convert BluetoothDevice to BluetoothDeviceEntity
@SuppressLint("MissingPermission")
private fun BluetoothDevice.toBluetoothDeviceEntity(rssi: Int): BluetoothDeviceEntity {
    val deviceName = try {
        this.name ?: "Unknown Device"
    } catch (e: SecurityException) {
        "Unknown Device"
    }

    val deviceType = when (this.bluetoothClass?.majorDeviceClass) {
        0x0100 -> DeviceType.COMPUTER
        0x0200 -> DeviceType.PHONE
        0x0400 -> DeviceType.AUDIO_VIDEO
        0x0500 -> DeviceType.PERIPHERAL
        0x0600 -> DeviceType.IMAGING
        0x0700 -> DeviceType.WEARABLE
        0x0800 -> DeviceType.TOY
        0x0900 -> DeviceType.HEALTH
        else -> DeviceType.UNKNOWN
    }

    return BluetoothDeviceEntity(
        name = deviceName,
        address = this.address,
        deviceType = deviceType,
        rssi = if (rssi != Short.MIN_VALUE.toInt()) rssi else null
    )
}