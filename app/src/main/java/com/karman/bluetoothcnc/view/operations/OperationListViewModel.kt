package com.karman.bluetoothcnc.view.operations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karman.bluetoothcnc.base.BaseViewModel

class OperationListViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Plasma Bank Fragment"
    }
    val text: LiveData<String> = _text
}