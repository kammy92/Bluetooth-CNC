package com.karman.bluetoothcnc.binding

import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.karman.bluetoothcnc.util.InputFilterMinMax

/***
 * This is the binding adapter for the views which are bind using databinding
 * handle here for all the required cases....
 *
 * @author Karman Singh on November 10, 2020
 */

@BindingAdapter("setSpeedMaxFilter")
fun setSpeedMaxFilter(editText: EditText, maxValue:Int){
    editText.filters = arrayOf(InputFilterMinMax(1, maxValue))
}

@BindingAdapter("setOnTouchListener")
fun setOnTouchListener(editText: EditText, maxValue: Int) {
    editText.filters = arrayOf(InputFilterMinMax(1, maxValue))
}