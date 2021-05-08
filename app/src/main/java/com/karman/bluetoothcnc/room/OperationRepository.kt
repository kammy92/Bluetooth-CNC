package com.karman.bluetoothcnc.room

import androidx.lifecycle.LiveData

class OperationRepository(private val operationDao: OperationDao) {

    val allOperations: LiveData<List<Operation>> = operationDao.getAllOperations()

    suspend fun insert(operation: Operation) {
        operationDao.insert(operation)
    }

    suspend fun update(operation: Operation,
            type: Int, speed: Int, duration: Int, startDelay: Int, endDelay: Int) {
        operationDao.update(operation.updateOperation(type, speed, duration, startDelay, endDelay))
    }

    suspend fun delete(operation: Operation) {
        operationDao.delete(operation)
    }

}