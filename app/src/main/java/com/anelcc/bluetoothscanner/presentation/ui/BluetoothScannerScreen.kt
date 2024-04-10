package com.anelcc.bluetoothscanner.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anelcc.bluetoothscanner.R
import com.anelcc.bluetoothscanner.domain.ScanState
import com.anelcc.bluetoothscanner.presentation.ui.components.DeviceList
import com.anelcc.bluetoothscanner.presentation.ui.components.ErrorCard
import com.anelcc.bluetoothscanner.presentation.ui.components.HeaderCard
import com.anelcc.bluetoothscanner.presentation.ui.components.ScanningStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BluetoothScannerScreen(
    state: ScanState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onClearError: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = colorResource(R.color.purple_20))
        ) {
            // Header Card
            HeaderCard(
                isScanning = state.isScanning,
                onStartScan = onStartScan,
                onStopScan = onStopScan
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Error Display
            state.error?.let { error ->
                ErrorCard(
                    error = error,
                    onDismiss = onClearError
                )
            }

            // Status Text
            ScanningStatus(state.isScanning, state.devices.size)
            Spacer(modifier = Modifier.height(8.dp))

            // Device List
            DeviceList(devices = state.devices)
        }
    }

}