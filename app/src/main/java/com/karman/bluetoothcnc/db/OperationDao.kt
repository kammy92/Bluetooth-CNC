package com.karman.bluetoothcnc.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OperationDao {

    @Query("SELECT * FROM operations")
    fun getAllOperation(): List<Operation>

    @Insert
    fun insertAllOperations(operationList: List<Operation>)

    @Delete
    fun deleteOperation(operation: Operation)
}