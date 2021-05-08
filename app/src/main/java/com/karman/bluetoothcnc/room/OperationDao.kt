package com.karman.bluetoothcnc.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OperationDao {

    @Query("SELECT * FROM Operation")
    fun getAllOperations(): LiveData<List<Operation>>

    @Query("SELECT * FROM Operation WHERE id=:id")
    fun getSingleOperationById(id: Int): LiveData<Operation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(operation: Operation)

    @Update
    suspend fun update(operation: Operation)

    @Delete
    suspend fun delete(operation: Operation)
}