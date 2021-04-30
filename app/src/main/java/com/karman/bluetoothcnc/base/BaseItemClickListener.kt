package com.karman.bluetoothcnc.base

interface BaseItemClickListener<in T> {
    fun onItemClick(mPosition: Int, mSelectedItem: T)
}
