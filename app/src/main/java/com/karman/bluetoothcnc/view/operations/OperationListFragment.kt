package com.karman.bluetoothcnc.view.operations

import android.os.Bundle
import android.view.View
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.base.BaseFragment
import com.karman.bluetoothcnc.databinding.FragmentOperationListBinding
import com.karman.bluetoothcnc.listener.AppClickListener
import com.karman.bluetoothcnc.listener.OperationItemClickListener
import com.karman.bluetoothcnc.room.Operation

class OperationListFragment : BaseFragment<FragmentOperationListBinding, OperationListViewModel>
(R.layout.fragment_operation_list, OperationListViewModel::class) {
    private val operationItemClickListener = object : OperationItemClickListener {
        override fun onEditClick(operation: Operation) {
            viewModel.editOperation(operation,
                    type = arrayListOf(-1, 1, 2, 3, 4).random(),
                    speed = (100..255).random(),
                    duration = (1000..3000).random(),
                    startDelay = (500..1000).random(),
                    endDelay = (500..1000).random())
        }

        override fun onDeleteClick(operation: Operation) {
            viewModel.deleteOperation(operation)
        }
    }

    private val operationClickListener = object : AppClickListener.OperationClickListener {
        override fun onAddOperationCLick() {
            viewModel.insertOperation(Operation(
                    type = arrayListOf(-1, 1, 2, 3, 4).random(),
                    speed = (100..255).random(),
                    duration = (1000..3000).random(),
                    startDelay = (500..1000).random(),
                    endDelay = (500..1000).random()))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding!!.operationItemClickListener = operationItemClickListener
        dataBinding!!.operationClickListener = operationClickListener
    }
}