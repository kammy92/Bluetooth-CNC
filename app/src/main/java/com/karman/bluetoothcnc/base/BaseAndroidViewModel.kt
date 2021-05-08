package com.karman.bluetoothcnc.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {
    private val isLoading = ObservableBoolean(false)

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    fun getIsLoading(): Boolean {
        return isLoading.get()
    }
}
