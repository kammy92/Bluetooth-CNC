package com.karman.bluetoothcnc.view.home

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.SharedViewModel
import com.karman.bluetoothcnc.base.BaseFragment
import com.karman.bluetoothcnc.databinding.FragmentHomeBinding
import com.karman.bluetoothcnc.listener.AppClickListener
import com.karman.bluetoothcnc.model.Operation
import com.karman.bluetoothcnc.util.EventObserver
import com.karman.bluetoothcnc.view.MainActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>
(R.layout.fragment_home, HomeViewModel::class) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    var mBluetoothSocket: BluetoothSocket? = null

    var deviceConnectedThread: DeviceConnectedThread? = null
    var connectDeviceThread: ConnectDeviceThread? = null

    private val appClickListener = object : AppClickListener {
        override fun onBluetoothConnectClick() {
            findNavController().navigate(
                    R.id.action_navigation_home_to_deviceListFragment
            )
            (baseActivity as MainActivity).shouldShowBottomNavigation(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding!!.appClickListener = appClickListener
        dataBinding!!.tvSendData.setOnClickListener {
            val operationList: ArrayList<Operation> = ArrayList<Operation>()
            operationList.add(Operation(UNIT_FORWARD, 255, 3000, 300, 300))
            operationList.add(Operation(TOOL_FORWARD, 255, 2000, 300, 300))
            operationList.add(Operation(UNIT_FORWARD, 180, 300, 300, 300))
            operationList.add(Operation(TOOL_BACKWARD, 180, 700, 300, 300))
            operationList.add(Operation(TOOL_FORWARD, 180, 200, 300, 300))
            operationList.add(Operation(UNIT_FORWARD, 180, 2000, 300, 300))
            operationList.add(Operation(TOOL_BACKWARD, 180, 300, 300, 300))
            operationList.add(Operation(UNIT_BACKWARD, 255, 5000, 300, 300))

//            operationList.add(Operation( 5, 255, 3000, 1, 1, 500))
//            operationList.add(Operation( 7, 255, 2000, 1, 2, 500))
//            operationList.add(Operation( 9, 200, 500, 1, 1, 500))
//            operationList.add(Operation( 12, 200, 1000, 0, 2, 500))
//            operationList.add(Operation(0, 500, 11, 200, 200, 1, 2, 500))
//            operationList.add(Operation(0, 500, 9, 200, 2000, 1, 1, 500))
////            operationList.add(Operation(0, 500, 12, 200, 200, 0, 2, 500))
////            operationList.add(Operation(0, 500, 6, 255, 5000, 0, 1, 500))
            val jsonObject = JSONObject()
            try {
                jsonObject.put(
                        "op", JSONArray(Gson().toJson(operationList,
                        object : TypeToken<List<Operation?>?>() {}.type)))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            deviceConnectedThread?.write("$jsonObject;")
        }



        dataBinding!!.tv1.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(UNIT_FORWARD, 255, -1, 0, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put(
                            "op", JSONArray(
                            Gson().toJson(
                                    operationList,
                                    object : TypeToken<List<Operation?>?>() {}.type
                            )
                    )
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                deviceConnectedThread?.write("$jsonObject;")
            } else if (event.action == MotionEvent.ACTION_UP) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(ALL_OPERATION_OFF, 0, -1, 0, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put(
                            "op", JSONArray(
                            Gson().toJson(
                                    operationList,
                                    object : TypeToken<List<Operation?>?>() {}.type
                            )
                    )
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                deviceConnectedThread?.write("$jsonObject;")
            }
            false
        })

        dataBinding!!.tv2.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(UNIT_BACKWARD, 255, -1, 0, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put(
                            "op", JSONArray(
                            Gson().toJson(
                                    operationList,
                                    object : TypeToken<List<Operation?>?>() {}.type
                            )
                    )
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                deviceConnectedThread?.write("$jsonObject;")
            } else if (event.action == MotionEvent.ACTION_UP) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(ALL_OPERATION_OFF, 0, -1, 0, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put(
                            "op", JSONArray(
                            Gson().toJson(
                                    operationList,
                                    object : TypeToken<List<Operation?>?>() {}.type
                            )
                    )
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                deviceConnectedThread?.write("$jsonObject;")
            }
            false
        })

        dataBinding!!.tv3.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(TOOL_FORWARD, 255, -1, 0, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put(
                            "op", JSONArray(
                            Gson().toJson(
                                    operationList,
                                    object : TypeToken<List<Operation?>?>() {}.type
                            )
                    )
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                deviceConnectedThread?.write("$jsonObject;")
            } else if (event.action == MotionEvent.ACTION_UP) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(ALL_OPERATION_OFF, 0, -1, 0, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put(
                            "op", JSONArray(
                            Gson().toJson(
                                    operationList,
                                    object : TypeToken<List<Operation?>?>() {}.type
                            )
                    )
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                deviceConnectedThread?.write("$jsonObject;")
            }
            false
        })

        dataBinding!!.tv4.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(TOOL_BACKWARD, 255, -1, 0, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put(
                            "op", JSONArray(
                            Gson().toJson(
                                    operationList,
                                    object : TypeToken<List<Operation?>?>() {}.type
                            )
                    )
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                deviceConnectedThread?.write("$jsonObject;")
            } else if (event.action == MotionEvent.ACTION_UP) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(ALL_OPERATION_OFF, 0, -1, 0, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put(
                            "op", JSONArray(
                            Gson().toJson(
                                    operationList,
                                    object : TypeToken<List<Operation?>?>() {}.type
                            )
                    )
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                deviceConnectedThread?.write("$jsonObject;")
            }
            false
        })

        sharedViewModel.onDeviceSelected.observe(baseActivity!!, EventObserver {
            viewModel.selectedDevice = it
            findNavController().popBackStack()
            viewModel.setConnectionStatus("Connecting to ${it.deviceName}")
            connectDeviceThread = ConnectDeviceThread(it.deviceAddress)
            connectDeviceThread!!.start()
        })
    }

    inner class ConnectDeviceThread(deviceAddress: String?) : Thread() {
        override fun run() {
            // Cancel discovery because it otherwise slows down the connection.
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
            try {
                // Connect to the remote device through the socket.
                // This call blocks run until it succeeds or throws an exception.
                mBluetoothSocket?.connect()
                Log.e(LOG_STATUS_TAG, "Device connected Successfully")
                viewModel.deviceMessageHandler?.obtainMessage(CONNECTION_STATUS,
                        CONNECTION_SUCCESSFUL, -1)?.sendToTarget()
            } catch (connectException: IOException) {
                // Unable to connect. Close the socket and return.
                try {
                    mBluetoothSocket?.close()
                    Log.e(LOG_STATUS_TAG, "Cannot connect to device")
                    viewModel.deviceMessageHandler?.obtainMessage(CONNECTION_STATUS,
                            CONNECTION_FAILED, -1)
                            ?.sendToTarget()
                } catch (exception: IOException) {
                    Log.e(LOG_ERROR_TAG, "Unable to Close Client Socket", exception)
                }
                return
            }
            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.
            deviceConnectedThread = DeviceConnectedThread(mBluetoothSocket)
            deviceConnectedThread?.run()
        }

        // Closes the client socket and causes the thread to finish.
        fun cancel() {
            try {
                mBluetoothSocket?.close()
            } catch (exception: IOException) {
                Log.e(LOG_ERROR_TAG, "Unable to Close Client Socket", exception)
            }
        }

        init {
            val bluetoothDevice =
                    BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress)
            var tempBluetoothSocket: BluetoothSocket? = null
            val uuid = bluetoothDevice.uuids[0].uuid
            try {
                // Get a BluetoothSocket to connect with the given BluetoothDevice.
                // Due to Android device varieties,the method below may not work for
                // different devices. You should try using other methods i.e. :
                // tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
                tempBluetoothSocket =
                        bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid)
            } catch (exception: Exception) {
                Log.e(LOG_ERROR_TAG, "Unable to Create Socket Connection", exception)
            }
            mBluetoothSocket = tempBluetoothSocket
        }
    }

    inner class DeviceConnectedThread(private val mBluetoothSocket: BluetoothSocket?) : Thread() {
        private val mInputStream: InputStream?
        private val mOutputStream: OutputStream?
        override fun run() {
            val byteArray = ByteArray(1024) // buffer store for the stream
            var bytes = 0 // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream from Arduino until termination character (\n) is
                    // reached. Then send the whole String message to GUI Handler.
                    byteArray[bytes] = mInputStream!!.read().toByte()
                    if (byteArray[bytes].toInt() == 10) {
                        val message = String(byteArray, 0, bytes)
                        Log.e(LOG_DATA_TAG, "Data Received: $message")
                        viewModel.deviceMessageHandler?.obtainMessage(MESSAGE_READ, message)
                                ?.sendToTarget()
                        bytes = 0
                    } else {
                        bytes++
                    }
                } catch (exception: Exception) {
                    Log.e(LOG_ERROR_TAG, "Unable to Receive Data", exception)
                    break
                }
            }
        }

        // Call this method to send data to the device
        fun write(data: String) {
            Log.e(LOG_DATA_TAG, "Data Sent: $data")
            val bytes = data.toByteArray() //converts entered String into bytes
            try {
                mOutputStream?.write(bytes)
            } catch (exception: Exception) {
                Log.e(LOG_ERROR_TAG, "Unable to Send Data", exception)
            }
        }

        // Call this method to shutdown and close the connection
        fun cancel() {
            try {
                mBluetoothSocket?.close()
            } catch (exception: Exception) {
                Log.e(LOG_ERROR_TAG, "Unable to Close Connection", exception)
            }
        }

        init {
            var tempInputStream: InputStream? = null
            var tempOutputStream: OutputStream? = null
            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tempInputStream = mBluetoothSocket?.inputStream
                tempOutputStream = mBluetoothSocket?.outputStream
            } catch (exception: Exception) {
                Log.e(LOG_ERROR_TAG, "Unable to Set Input and Output Streams", exception)
            }
            mInputStream = tempInputStream
            mOutputStream = tempOutputStream
        }
    }

    fun onBackPressed() {
        // Terminate Bluetooth Connection and close app
        if (connectDeviceThread != null) {
            connectDeviceThread!!.cancel()
        }
    }

    companion object {
        const val LOG_STATUS_TAG = "CNC STATUS LOG"
        const val LOG_DATA_TAG = "CNC DATA LOG"
        const val LOG_ERROR_TAG = "CNC ERROR LOG"
        const val CONNECTION_STATUS = 1
        const val MESSAGE_READ = 2
        const val CONNECTION_SUCCESSFUL = 1
        const val CONNECTION_FAILED = -1

        const val ALL_OPERATION_OFF = -1
        const val UNIT_FORWARD = 1
        const val UNIT_BACKWARD = 2
        const val TOOL_FORWARD = 3
        const val TOOL_BACKWARD = 4
    }
}