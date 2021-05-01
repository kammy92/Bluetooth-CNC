package com.karman.bluetoothcnc.binding

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.karman.bluetoothcnc.listener.AppClickListener
import com.karman.bluetoothcnc.util.InputFilterMinMax

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
    view.alpha = if (connectionStatus){
        1.0f
    } else {
        0.3f
    }
    view.isEnabled = connectionStatus
}