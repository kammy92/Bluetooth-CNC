package com.karman.bluetoothcnc.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karman.bluetoothcnc.model.Device

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _connectionStatus = MutableLiveData<String>()
    val connectionStatus: LiveData<String> = _connectionStatus

    var selectedDevice: Device? = null

    init {
        _connectionStatus.value = "Not Connected"
        _text.value = "This is Home Fragment"
    }

    fun setConnectionStatus(status: String) {
        _connectionStatus.value = status
    }
}