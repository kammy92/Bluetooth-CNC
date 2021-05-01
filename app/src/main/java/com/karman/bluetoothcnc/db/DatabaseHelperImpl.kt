package com.karman.bluetoothcnc.db

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override fun getAllOperations(): List<Operation> =
        appDatabase.operationDao().getAllOperation()

    override fun insertAllOperations(users: List<Operation>) =
        appDatabase.operationDao().insertAllOperations(users)

}