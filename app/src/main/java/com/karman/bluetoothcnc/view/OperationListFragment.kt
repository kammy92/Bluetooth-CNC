package com.karman.bluetoothcnc.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.karman.bluetoothcnc.HomeActivity
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.databinding.FragmentOperationListBinding

class OperationListFragment : Fragment() {

    private lateinit var operationListViewModel: OperationListViewModel
    private var dataBinding: FragmentOperationListBinding? = null
    private var homeActivity: HomeActivity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeActivity = context as HomeActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_operation_list, container, false)
        operationListViewModel =
            ViewModelProviders.of(this).get(OperationListViewModel::class.java)
        dataBinding!!.viewModel = operationListViewModel
        return dataBinding!!.root
    }

    override fun onDetach() {
        super.onDetach()
        homeActivity = null
    }
}