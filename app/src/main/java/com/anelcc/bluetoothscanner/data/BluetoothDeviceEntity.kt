package com.anelcc.bluetoothscanner.data

data class BluetoothDeviceEntity(
    val name: String,
    val address: String,
    val deviceType: DeviceType,
    val rssi: Int? = null
)

enum class DeviceType {
    COMPUTER, PHONE, AUDIO_VIDEO, PERIPHERAL, IMAGING, WEARABLE, TOY, HEALTH, UNKNOWN
}