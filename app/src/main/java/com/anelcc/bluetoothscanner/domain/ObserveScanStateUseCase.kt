package com.anelcc.bluetoothscanner.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class ObserveScanStateUseCase(private val repository: Any) {
    suspend operator fun invoke(): Flow<Boolean> {
//        return repository.observeScanState()
        return emptyFlow()
    }
}