package com.karman.bluetoothcnc.view.home

import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karman.bluetoothcnc.base.BaseViewModel
import com.karman.bluetoothcnc.model.Device

class HomeViewModel : BaseViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _connectionStatus = MutableLiveData<String>()
    val connectionStatus: LiveData<String> = _connectionStatus
    private val _deviceStatus = MutableLiveData<String>()
    val deviceStatus: LiveData<String> = _deviceStatus

    var selectedDevice: Device? = null

    var deviceMessageHandler: Handler? = null

    init {
        _connectionStatus.value = "Not Connected"
        _deviceStatus.value = ""
        _text.value = "This is Home Fragment"

        deviceMessageHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(message: Message) {
                when (message.what) {
                    HomeFragment.CONNECTION_STATUS -> when (message.arg1) {
                        HomeFragment.CONNECTION_SUCCESSFUL -> {
                            _connectionStatus.value =
                                    "Connected to ${selectedDevice?.deviceName}"
                        }
                        HomeFragment.CONNECTION_FAILED -> {
                            _connectionStatus.value = "Device fails to connect"
                        }
                    }
                    HomeFragment.MESSAGE_READ -> {
                        _deviceStatus.value = message.obj.toString()
                    }
                }
            }
        }
    }

    fun setConnectionStatus(status: String) {
        _connectionStatus.value = status
    }
}