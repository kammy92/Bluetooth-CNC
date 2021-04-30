package com.karman.bluetoothcnc.view.bluetooth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.SharedViewModel
import com.karman.bluetoothcnc.base.BaseFragment
import com.karman.bluetoothcnc.base.BaseItemClickListener
import com.karman.bluetoothcnc.databinding.FragmentDeviceListBinding
import com.karman.bluetoothcnc.model.Device
import com.karman.bluetoothcnc.util.setUpWithBaseAdapter
import kotlinx.android.synthetic.main.fragment_device_list.*

class DeviceListFragment : BaseFragment<FragmentDeviceListBinding, DeviceListViewModel>(
        R.layout.fragment_device_list, DeviceListViewModel::class) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val itemClickListener = object : BaseItemClickListener<Device> {
        override fun onItemClick(mPosition: Int, mSelectedItem: Device) {
            sharedViewModel.setOnDeviceSelected(mSelectedItem)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.deviceList.value?.let {
            rv_devices.setUpWithBaseAdapter(R.layout.list_item_device, it, itemClickListener)
        }
    }
}