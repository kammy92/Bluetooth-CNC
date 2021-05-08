package com.karman.bluetoothcnc.binding

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.adapter.OperationAdapter
import com.karman.bluetoothcnc.base.BaseItemClickListener
import com.karman.bluetoothcnc.listener.AppClickListener
import com.karman.bluetoothcnc.listener.OperationItemClickListener
import com.karman.bluetoothcnc.model.Device
import com.karman.bluetoothcnc.model.Operation.Companion.ALL_OPERATION_OFF
import com.karman.bluetoothcnc.model.Operation.Companion.TOOL_BACKWARD
import com.karman.bluetoothcnc.model.Operation.Companion.TOOL_FORWARD
import com.karman.bluetoothcnc.model.Operation.Companion.UNIT_BACKWARD
import com.karman.bluetoothcnc.model.Operation.Companion.UNIT_FORWARD
import com.karman.bluetoothcnc.room.Operation
import com.karman.bluetoothcnc.util.InputFilterMinMax
import com.karman.bluetoothcnc.util.setUpWithBaseAdapter

/***
 * This is the binding adapter for the views which are bind using databinding
 * handle here for all the required cases....
 *
 * @author Karman Singh on November 10, 2020
 */

@BindingAdapter("setSpeedMaxFilter")
fun setSpeedMaxFilter(editText: EditText, maxValue: Int) {
    editText.filters = arrayOf(InputFilterMinMax(1, maxValue))
}

@SuppressLint("ClickableViewAccessibility")
@BindingAdapter("setOperationType", "setAppClickListener")
fun setOnTouchListener(view: View, operationType: Int, appClickListener: AppClickListener) {
    view.setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_DOWN) {
            appClickListener.onManualOperationButtonPressed(operationType)
        } else if (event.action == MotionEvent.ACTION_UP) {
            appClickListener.onManualOperationButtonReleased()
        }
        false
    }
}

@BindingAdapter("setConnectionStatus")
fun setLayoutAlphaAndDisabled(view: View, connectionStatus: Boolean) {
    view.alpha = if (connectionStatus) {
        1.0f
    } else {
        0.3f
    }
    view.isEnabled = connectionStatus
}

@BindingAdapter("setDeviceList", "setDeviceItemClickListener")
fun setAdapterToDeviceList(recyclerView: RecyclerView, deviceList: ArrayList<Device>?,
        deviceItemClickListener: BaseItemClickListener<Device>) {
    deviceList?.let {
        recyclerView.setUpWithBaseAdapter(R.layout.list_item_device, it, deviceItemClickListener)
    }
}

@BindingAdapter("setOperationList", "setOperationItemClickListener")
fun setAdapterToOperationList(recyclerView: RecyclerView, operationList: List<Operation>?,
        operationItemClickListener: OperationItemClickListener) {
    operationList?.let {
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = OperationAdapter(it, operationItemClickListener)
    }
}

@BindingAdapter("setOperationType")
fun setOperationType(textView: TextView, operationType: Int) {
    textView.text = when (operationType) {
        ALL_OPERATION_OFF -> {
            "ALL OPERATION OFF"
        }
        UNIT_FORWARD -> {
            "UNIT FORWARD"
        }
        UNIT_BACKWARD -> {
            "UNIT BACKWARD"
        }
        TOOL_FORWARD -> {
            "TOOL FORWARD"
        }
        TOOL_BACKWARD -> {
            "TOOL BACKWARD"
        }
        else -> {
            "ALL OPERATION OFF"
        }
    }
}