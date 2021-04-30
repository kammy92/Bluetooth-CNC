package com.karman.bluetoothcnc.view.home

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
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

    var messageHandler: Handler? = null

    init {
        _connectionStatus.value = "Not Connected"
        _text.value = "This is Home Fragment"

        messageHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    HomeFragment.CONNECTION_STATUS -> when (msg.arg1) {
                        HomeFragment.CONNECTION_SUCCESSFUL -> {
                            setConnectionStatus(
                                    "Connected to ${selectedDevice?.deviceName}")
                        }
                        HomeFragment.CONNECTION_FAILED -> {
                            setConnectionStatus("Device fails to connect")
                        }
                    }
                    HomeFragment.MESSAGE_READ -> {
                        val arduinoMsg = msg.obj.toString() // Read message from Arduino
                        Log.e("karman", "Arduino message " + arduinoMsg)
                    }
                }
            }
        }

    }

    fun setConnectionStatus(status: String) {
        _connectionStatus.value = status
    }



}