package com.anelcc.bluetoothscanner.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anelcc.bluetoothscanner.data.BluetoothDeviceEntity
import com.anelcc.bluetoothscanner.data.DeviceType
import com.anelcc.bluetoothscanner.presentation.theme.BluetoothScannerTheme

@Composable
fun DeviceCard(device: BluetoothDeviceEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                /*text = device.address,*/
                text = "A1:B1:C1:D1:E1:F1",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = device.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = device.deviceType.name,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeviceCardPreview() {
    BluetoothScannerTheme {
        DeviceCard(
            BluetoothDeviceEntity("abc", "123", DeviceType.TOY)
        )
    }
}

