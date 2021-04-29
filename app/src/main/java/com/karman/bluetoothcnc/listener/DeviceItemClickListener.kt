package com.karman.bluetoothcnc.listener

import com.karman.bluetoothcnc.model.Device

interface DeviceItemClickListener {
    fun onDeviceItemClick(mSelectedDevice: Device)
}