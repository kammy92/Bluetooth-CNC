package com.karman.bluetoothcnc.listener

interface AppClickListener {
    fun onBluetoothConnectClick()
    fun onManualOperationButtonPressed(operationType:Int)
    fun onManualOperationButtonReleased()
    fun onStartAutoOperationsClick()
}
