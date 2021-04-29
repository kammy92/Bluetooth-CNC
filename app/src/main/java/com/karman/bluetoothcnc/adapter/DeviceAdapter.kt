package com.karman.bluetoothcnc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.databinding.ListItemDeviceBinding
import com.karman.bluetoothcnc.listener.DeviceItemClickListener
import com.karman.bluetoothcnc.model.Device

/**
 * This Adapter is used to handle the view implementation for
 * Sort type filter items in Filter screen
 *
 * @author Karman Singh on November 28, 2020
 */
class DeviceAdapter(var deviceList: List<Device>,
        var deviceItemClickListener: DeviceItemClickListener)
    : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemDeviceBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_item_device, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = deviceList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(deviceList[position])
    }

    inner class ViewHolder(private val binding: ListItemDeviceBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(device: Device) {
            binding.device = device
            binding.deviceItemClickListener = deviceItemClickListener
            binding.executePendingBindings()
        }
    }
}