package com.karman.bluetoothcnc.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.karman.bluetoothcnc.BR
import kotlin.reflect.KClass

abstract class BaseActivity<DataBinding : ViewDataBinding, ViewModel : androidx.lifecycle.ViewModel>
(@LayoutRes private val layoutResourceId: Int, private val viewModelClass: KClass<ViewModel>) :
        AppCompatActivity() {

    var dataBinding: DataBinding? = null
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
        dataBinding?.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(viewModelClass.java)
        dataBinding?.setVariable(BR.viewModel, viewModel)
    }
}

