package com.karman.bluetoothcnc.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Operation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun operationDao(): OperationDao
}