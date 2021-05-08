package com.karman.bluetoothcnc.listener

import com.karman.bluetoothcnc.room.Operation

interface OperationItemClickListener {
    fun onEditClick(operation: Operation)

    fun onDeleteClick(operation: Operation)
}
