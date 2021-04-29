package com.karman.bluetoothcnc.model

class Device(var deviceName: String, var deviceAddress: String) {
    override fun toString(): String {
        return "DeviceInfoModel(deviceName='$deviceName', deviceAddress='$deviceAddress')"
    }
}