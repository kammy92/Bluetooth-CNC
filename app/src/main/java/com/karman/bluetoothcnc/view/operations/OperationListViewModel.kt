package com.karman.bluetoothcnc.view.operations

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.karman.bluetoothcnc.base.BaseAndroidViewModel
import com.karman.bluetoothcnc.room.Operation
import com.karman.bluetoothcnc.room.OperationDatabase
import com.karman.bluetoothcnc.room.OperationRepository
import kotlinx.coroutines.launch

class OperationListViewModel(application: Application) : BaseAndroidViewModel(application) {

    private val operationRepository: OperationRepository = OperationRepository(
        OperationDatabase.getDatabase(application).operationDao()
    )
    val allOperationsFromDB: LiveData<List<Operation>>

    init {
        allOperationsFromDB = operationRepository.allOperations
    }

    fun insertOperation(operation: com.karman.bluetoothcnc.room.Operation) = viewModelScope.launch {
        operationRepository.insert(operation)
    }

    fun editOperation(
        operation: com.karman.bluetoothcnc.room.Operation, type: Int, speed: Int,
        duration: Int, startDelay: Int, endDelay: Int
    ) = viewModelScope.launch {
        operationRepository.update(operation, type, speed, duration, startDelay, endDelay)
    }

    fun deleteOperation(operation: com.karman.bluetoothcnc.room.Operation) = viewModelScope.launch {
        operationRepository.delete(operation)
    }
}