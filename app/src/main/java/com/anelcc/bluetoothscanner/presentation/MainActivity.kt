package com.anelcc.bluetoothscanner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.anelcc.bluetoothscanner.presentation.theme.BluetoothScannerTheme
import com.anelcc.bluetoothscanner.presentation.ui.ListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BluetoothScannerTheme {
                ListScreen()
            }
        }
    }
}