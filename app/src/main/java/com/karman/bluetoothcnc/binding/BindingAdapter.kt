package com.karman.bluetoothcnc.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.karman.bluetoothcnc.adapter.DeviceAdapter
import com.karman.bluetoothcnc.listener.DeviceItemClickListener
import com.karman.bluetoothcnc.model.Device

/***
 * This is the binding adapter for the views which are bind using databinding
 * handle here for all the required cases....
 *
 * @author Karman Singh on November 10, 2020
 */

@BindingAdapter("setDeviceList", "setDeviceItemClickListener")
fun setAdapterToDeviceList(recyclerView: RecyclerView,
        mDeviceList: List<Device>?, deviceItemClickListener: DeviceItemClickListener) {
    mDeviceList?.let {
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = DeviceAdapter(it, deviceItemClickListener)
    }
}