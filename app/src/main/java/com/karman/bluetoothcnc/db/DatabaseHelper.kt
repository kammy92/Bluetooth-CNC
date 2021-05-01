package com.karman.bluetoothcnc.db

interface DatabaseHelper {
    fun getAllOperations(): List<Operation>
    fun insertAllOperations(users: List<Operation>)
}