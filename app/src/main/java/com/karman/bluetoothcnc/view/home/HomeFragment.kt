package com.karman.bluetoothcnc.view.home

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karman.bluetoothcnc.HomeActivity
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.SharedViewModel
import com.karman.bluetoothcnc.databinding.FragmentHomeBinding
import com.karman.bluetoothcnc.model.Operation
import com.karman.bluetoothcnc.util.EventObserver
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class HomeFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var homeViewModel: HomeViewModel
    private var dataBinding: FragmentHomeBinding? = null
    private var homeActivity: HomeActivity? = null

    var handler: Handler? = null
    var mmSocket: BluetoothSocket? = null
    var connectedThread: ConnectedThread? = null
    var createConnectThread: CreateConnectThread? = null

    private val CONNECTING_STATUS = 1 // used in bluetooth handler to identify message status

    private val MESSAGE_READ = 2 // used in bluetooth handler to identify message update

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeActivity = context as HomeActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
Second most important piece of Code. GUI Handler
 */
        handler =
                object : Handler(Looper.getMainLooper()) {
                    override fun handleMessage(msg: Message) {
                        when (msg.what) {
                            CONNECTING_STATUS -> when (msg.arg1) {
                                1 -> {
                                    homeViewModel.setConnectionStatus(
                                            "Connected to ${homeViewModel.selectedDevice?.deviceName}")

//                                toolbar.setSubtitle("Connected to $deviceName")
//                                progressBar.setVisibility(View.GONE)
//                                buttonConnect.setEnabled(true)
//                                buttonToggle.setEnabled(true)
                                }
                                -1 -> {
                                    homeViewModel.setConnectionStatus("Device fails to connect")
//                                toolbar.setSubtitle("Device fails to connect")
//                                progressBar.setVisibility(View.GONE)
//                                buttonConnect.setEnabled(true)
                                }
                            }
                            MESSAGE_READ -> {
                                val arduinoMsg = msg.obj.toString() // Read message from Arduino
                                when (arduinoMsg.toLowerCase()) {
                                    "led is turned on" -> {
//                                    imageView.setBackgroundColor(resources.getColor(R.color.colorOn))
//                                    textViewInfo.setText("Arduino Message : $arduinoMsg")
                                    }
                                    "led is turned off" -> {
//                                    imageView.setBackgroundColor(resources.getColor(R.color.colorOff))
//                                    textViewInfo.setText("Arduino Message : $arduinoMsg")
                                    }
                                }
                            }
                        }
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        dataBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        dataBinding!!.lifecycleOwner = this;
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        dataBinding!!.viewModel = homeViewModel
        return dataBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding!!.tvText.setOnClickListener {
            findNavController().navigate(
                    R.id.action_navigation_home_to_deviceListFragment)
        }

        dataBinding!!.tvSendData.setOnClickListener {
            val operationList: ArrayList<Operation> = ArrayList<Operation>()
            operationList.add(Operation(0, 500, 5, 255, 3000, 1, 1, 500))
            operationList.add(Operation(0, 500, 7, 255, 2000, 1, 2, 500))
            operationList.add(Operation(0, 500, 9, 200, 500, 1, 1, 500))
            operationList.add(Operation(0, 500, 12, 200, 1000, 0, 2, 500))
            operationList.add(Operation(0, 500, 11, 200, 200, 1, 2, 500))
            operationList.add(Operation(0, 500, 9, 200, 2000, 1, 1, 500))
//            operationList.add(Operation(0, 500, 12, 150, 200, 0, 2, 500))
//            operationList.add(Operation(0, 500, 6, 255, 5000, 0, 1, 500))
            val jsonObject = JSONObject()
            try {
                jsonObject.put("op", JSONArray(Gson().toJson(operationList,
                        object : TypeToken<List<Operation?>?>() {}.type)))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            connectedThread?.write("$jsonObject;")
        }



        dataBinding!!.tv1.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(1, 0, 1, 255, 0, 1, 1, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("op", JSONArray(Gson().toJson(operationList,
                            object : TypeToken<List<Operation?>?>() {}.type)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                connectedThread?.write("$jsonObject;")
            } else if (event.action == MotionEvent.ACTION_UP) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(1, 0, -1, 0, 0, -1, 1, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("op", JSONArray(Gson().toJson(operationList,
                            object : TypeToken<List<Operation?>?>() {}.type)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                connectedThread?.write("$jsonObject;")
            }
            false
        })

        dataBinding!!.tv2.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(1, 0, 2, 255, 0, 0, 1, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("op", JSONArray(Gson().toJson(operationList,
                            object : TypeToken<List<Operation?>?>() {}.type)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                connectedThread?.write("$jsonObject;")
            } else if (event.action == MotionEvent.ACTION_UP) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(1, 0, -1, 0, 0, -1, 1, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("op", JSONArray(Gson().toJson(operationList,
                            object : TypeToken<List<Operation?>?>() {}.type)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                connectedThread?.write("$jsonObject;")
            }
            false
        })

        dataBinding!!.tv3.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(1, 0, 3, 255, 0, 1, 2, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("op", JSONArray(Gson().toJson(operationList,
                            object : TypeToken<List<Operation?>?>() {}.type)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                connectedThread?.write("$jsonObject;")
            } else if (event.action == MotionEvent.ACTION_UP) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(1, 0, -1, 0, 0, -1, 2, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("op", JSONArray(Gson().toJson(operationList,
                            object : TypeToken<List<Operation?>?>() {}.type)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                connectedThread?.write("$jsonObject;")
            }
            false
        })

        dataBinding!!.tv4.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(1, 0, 4, 255, 0, 0, 2, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("op", JSONArray(Gson().toJson(operationList,
                            object : TypeToken<List<Operation?>?>() {}.type)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                connectedThread?.write("$jsonObject;")
            } else if (event.action == MotionEvent.ACTION_UP) {
                val operationList: ArrayList<Operation> = ArrayList<Operation>()
                operationList.add(Operation(1, 0, -1, 0, 0, -1, 2, 0))
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("op", JSONArray(Gson().toJson(operationList,
                            object : TypeToken<List<Operation?>?>() {}.type)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                connectedThread?.write("$jsonObject;")
            }
            false
        })




        sharedViewModel.onDeviceSelected.observe(homeActivity!!, EventObserver {
            homeViewModel.selectedDevice = it
            findNavController().popBackStack()
            Log.e("karman", it.toString())
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            homeViewModel.setConnectionStatus("Connecting to ${it.deviceName}")
            createConnectThread = CreateConnectThread(bluetoothAdapter, it.deviceAddress)
            createConnectThread!!.start()
        })
    }

    override fun onDetach() {
        super.onDetach()
        homeActivity = null
    }

    /* ============================ Thread to Create Bluetooth Connection =================================== */
    inner class CreateConnectThread(bluetoothAdapter: BluetoothAdapter, address: String?) :
            Thread() {
        override fun run() {
            // Cancel discovery because it otherwise slows down the connection.
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            bluetoothAdapter.cancelDiscovery()
            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mmSocket?.connect()
                Log.e("Status", "Device connected")
                handler?.obtainMessage(CONNECTING_STATUS, 1, -1)?.sendToTarget()
            } catch (connectException: IOException) {
                // Unable to connect; close the socket and return.
                try {
                    mmSocket?.close()
                    Log.e("Status", "Cannot connect to device")
                    handler?.obtainMessage(CONNECTING_STATUS, -1, -1)?.sendToTarget()
                } catch (closeException: IOException) {
                    Log.e(ContentValues.TAG, "Could not close the client socket", closeException)
                }
                return
            }

            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.
            connectedThread = ConnectedThread(mmSocket!!)
            connectedThread!!.run()
        }

        // Closes the client socket and causes the thread to finish.
        fun cancel() {
            try {
                mmSocket?.close()
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "Could not close the client socket", e)
            }
        }

        init {
            /*
            Use a temporary object that is later assigned to mmSocket
            because mmSocket is final.
             */
            val bluetoothDevice = bluetoothAdapter.getRemoteDevice(address)
            var tmp: BluetoothSocket? = null
            val uuid = bluetoothDevice.uuids[0].uuid
            try {
                /*
                Get a BluetoothSocket to connect with the given BluetoothDevice.
                Due to Android device varieties,the method below may not work fo different devices.
                You should try using other methods i.e. :
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
                 */
                tmp = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid)
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "Socket's create() method failed", e)
            }
            mmSocket = tmp
        }
    }

    /* =============================== Thread for Data Transfer =========================================== */
    inner class ConnectedThread(private val mmSocket: BluetoothSocket) : Thread() {
        private val mmInStream: InputStream?
        private val mmOutStream: OutputStream?
        override fun run() {
            val buffer = ByteArray(1024) // buffer store for the stream
            var bytes = 0 // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    /*
                    Read from the InputStream from Arduino until termination character is reached.
                    Then send the whole String message to GUI Handler.
                     */
                    buffer[bytes] = mmInStream!!.read().toByte()
                    var readMessage: String
                    if (buffer[bytes].toString().equals('\n')) { // isko dekhna hai
                        readMessage = String(buffer, 0, bytes)
                        Log.e("Arduino Message", readMessage)
                        handler?.obtainMessage(MESSAGE_READ, readMessage)?.sendToTarget()
                        bytes = 0
                    } else {
                        bytes++
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    break
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        fun write(input: String) {
            val bytes = input.toByteArray() //converts entered String into bytes
            try {
                mmOutStream!!.write(bytes)
            } catch (e: IOException) {
                Log.e("Send Error", "Unable to send message", e)
            }
        }

        /* Call this from the main activity to shutdown the connection */
        fun cancel() {
            try {
                mmSocket.close()
            } catch (e: IOException) {
            }
        }

        init {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = mmSocket.inputStream
                tmpOut = mmSocket.outputStream
            } catch (e: IOException) {
            }
            mmInStream = tmpIn
            mmOutStream = tmpOut
        }
    }

    /* ============================ Terminate Connection at BackPress ====================== */
    fun onBackPressed() {
        // Terminate Bluetooth Connection and close app
        if (createConnectThread != null) {
            createConnectThread!!.cancel()
        }
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}