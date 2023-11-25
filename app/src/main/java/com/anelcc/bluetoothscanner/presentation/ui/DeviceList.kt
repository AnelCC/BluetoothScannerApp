package com.anelcc.bluetoothscanner.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anelcc.bluetoothscanner.presentation.theme.BluetoothScannerTheme

@Composable
fun DeviceList (devices: List<Any>) {

    LazyColumn(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(devices.size) { id ->
            DeviceCard()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DeviceListPreview() {
    BluetoothScannerTheme {
        DeviceList(listOf(Any(), Any(), Any(),Any(),Any()))
    }
}