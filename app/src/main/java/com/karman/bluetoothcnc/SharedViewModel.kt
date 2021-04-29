package com.karman.bluetoothcnc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karman.bluetoothcnc.model.Device
import com.karman.bluetoothcnc.util.Event

class SharedViewModel : ViewModel() {
    private val _onDeviceSelected: MutableLiveData<Event<Device>> = MutableLiveData<Event<Device>>()
    val onDeviceSelected: LiveData<Event<Device>> = _onDeviceSelected

    fun setOnDeviceSelected(mSelectedDevice: Device) {
        _onDeviceSelected.value = Event(mSelectedDevice)
    }
}