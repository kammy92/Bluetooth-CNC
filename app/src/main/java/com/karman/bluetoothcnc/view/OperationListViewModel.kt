package com.karman.bluetoothcnc.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OperationListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Plasma Bank Fragment"
    }
    val text: LiveData<String> = _text
}