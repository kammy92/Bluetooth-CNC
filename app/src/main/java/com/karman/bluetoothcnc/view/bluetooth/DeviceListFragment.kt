package com.karman.bluetoothcnc.view.bluetooth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.SharedViewModel
import com.karman.bluetoothcnc.base.BaseFragment
import com.karman.bluetoothcnc.databinding.FragmentDeviceListBinding
import com.karman.bluetoothcnc.listener.DeviceItemClickListener
import com.karman.bluetoothcnc.model.Device

class DeviceListFragment : BaseFragment<FragmentDeviceListBinding, DeviceListViewModel>(
        R.layout.fragment_device_list, DeviceListViewModel::class) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val deviceItemClickListener = object : DeviceItemClickListener {
        override fun onDeviceItemClick(mSelectedDevice: Device) {
            sharedViewModel.setOnDeviceSelected(mSelectedDevice)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding!!.deviceItemClickListener = deviceItemClickListener
    }
}