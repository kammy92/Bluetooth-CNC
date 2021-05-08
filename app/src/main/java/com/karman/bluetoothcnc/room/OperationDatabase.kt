package com.karman.bluetoothcnc.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Operation::class], version = 1, exportSchema = false)
abstract class OperationDatabase : RoomDatabase() {
    abstract fun operationDao(): OperationDao

    companion object {
        var DATABASE_NAME = "my_database"

        @Volatile
        private var INSTANCE: OperationDatabase? = null
        fun getDatabase(context: Context): OperationDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OperationDatabase::class.java, DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}