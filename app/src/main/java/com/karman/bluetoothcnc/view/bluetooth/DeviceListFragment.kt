package com.karman.bluetoothcnc.view.bluetooth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import com.karman.bluetoothcnc.HomeActivity
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.SharedViewModel
import com.karman.bluetoothcnc.databinding.FragmentDeviceListBinding
import com.karman.bluetoothcnc.listener.DeviceItemClickListener
import com.karman.bluetoothcnc.model.Device

class DeviceListFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var deviceListViewModel: DeviceListViewModel
    private var dataBinding: FragmentDeviceListBinding? = null
    var homeActivity: HomeActivity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeActivity = context as HomeActivity
    }

    private val deviceItemClickListener = object : DeviceItemClickListener {
        override fun onDeviceItemClick(mSelectedDevice: Device) {
            sharedViewModel.setOnDeviceSelected(mSelectedDevice)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        dataBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_device_list, container, false)
        deviceListViewModel =
                ViewModelProviders.of(this).get(DeviceListViewModel::class.java)
        dataBinding!!.viewModel = deviceListViewModel
        dataBinding!!.deviceItemClickListener = deviceItemClickListener
        return dataBinding!!.root
    }

    override fun onDetach() {
        super.onDetach()
        homeActivity = null
    }
}