package com.karman.bluetoothcnc.view.bluetooth

import android.bluetooth.BluetoothAdapter
import androidx.lifecycle.MutableLiveData
import com.karman.bluetoothcnc.base.BaseViewModel
import com.karman.bluetoothcnc.model.Device

class DeviceListViewModel : BaseViewModel() {

    var deviceList: MutableLiveData<ArrayList<Device>> = MutableLiveData()

    init {
        deviceList.value = ArrayList()
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val pairedDevices = bluetoothAdapter.bondedDevices
        if (pairedDevices.size > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (device in pairedDevices) {
                deviceList.value!!.add(Device(device.name, device.address))
            }
        }


        if (bluetoothAdapter == null) {
            // Device does not support Bluetooth
        } else if (!bluetoothAdapter.isEnabled) {
            // Bluetooth is not enabled :)
        } else {
            // Bluetooth is enabled
        }
    }
}