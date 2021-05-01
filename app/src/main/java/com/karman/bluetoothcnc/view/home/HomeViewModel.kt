package com.karman.bluetoothcnc.view.home

import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karman.bluetoothcnc.base.BaseViewModel
import com.karman.bluetoothcnc.model.Device
import com.karman.bluetoothcnc.model.Operation
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class HomeViewModel : BaseViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _connectionStatus = MutableLiveData<String>()
    val connectionStatus: LiveData<String> = _connectionStatus
    private val _deviceStatus = MutableLiveData<String>()
    val deviceStatus: LiveData<String> = _deviceStatus

    var isConnectionSuccessful = ObservableBoolean(false)

    var selectedDevice: Device? = null

    var deviceMessageHandler: Handler? = null

    val speedUnitForward = MutableLiveData<String>()
    val speedUnitBackward = MutableLiveData<String>()
    val speedToolForward = MutableLiveData<String>()
    val speedToolBackward = MutableLiveData<String>()

    private val _operationList = MutableLiveData<ArrayList<Operation>>()
    val operationList: LiveData<ArrayList<Operation>> = _operationList

    init {
        _connectionStatus.value = "Not Connected"
        _deviceStatus.value = "ALL OFF"
        _text.value = "This is Home Fragment"
        speedUnitForward.value = "255"
        speedUnitBackward.value = "255"
        speedToolForward.value = "255"
        speedToolBackward.value = "255"
        setManualOperation(HomeFragment.ALL_OPERATION_OFF)

        deviceMessageHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(message: Message) {
                when (message.what) {
                    HomeFragment.CONNECTION_STATUS -> when (message.arg1) {
                        HomeFragment.CONNECTION_SUCCESSFUL -> {
                            _connectionStatus.value =
                                    "Connected to ${selectedDevice?.deviceName}"
                            isConnectionSuccessful.set(true)
                        }
                        HomeFragment.CONNECTION_FAILED -> {
                            _connectionStatus.value = "Device fails to connect"
                            isConnectionSuccessful.set(false)
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

    fun setOperationList(operationList: ArrayList<Operation>) {
        _operationList.value = operationList
    }

    fun setManualOperation(operationType: Int) {
        _operationList.value = when (operationType) {
            HomeFragment.ALL_OPERATION_OFF -> {
                arrayListOf(Operation(HomeFragment.ALL_OPERATION_OFF, 0))
            }
            HomeFragment.UNIT_FORWARD -> {
                arrayListOf(Operation(HomeFragment.UNIT_FORWARD, speedUnitForward.value!!.toInt()))
            }
            HomeFragment.UNIT_BACKWARD -> {
                arrayListOf(
                        Operation(HomeFragment.UNIT_BACKWARD, speedUnitBackward.value!!.toInt()))
            }
            HomeFragment.TOOL_FORWARD -> {
                arrayListOf(Operation(HomeFragment.TOOL_FORWARD, speedToolForward.value!!.toInt()))
            }
            HomeFragment.TOOL_BACKWARD -> {
                arrayListOf(
                        Operation(HomeFragment.TOOL_BACKWARD, speedToolBackward.value!!.toInt()))
            }
            else -> {
                arrayListOf(Operation(HomeFragment.ALL_OPERATION_OFF, 0))
            }
        }
    }

    fun getOperationJson(operationList: ArrayList<Operation>): String {
        var operationJson = ""
        val jsonObject = JSONObject()
        try {
            jsonObject.put("op", JSONArray(Gson().toJson(operationList,
                    object : TypeToken<List<Operation?>?>() {}.type)))
            operationJson = jsonObject.toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return operationJson
    }
}