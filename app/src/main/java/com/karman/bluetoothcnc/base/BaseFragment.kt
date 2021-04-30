package com.karman.bluetoothcnc.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.karman.bluetoothcnc.BR
import kotlin.reflect.KClass

abstract class BaseFragment<DataBinding : ViewDataBinding, ViewModel : androidx.lifecycle.ViewModel>
(@LayoutRes private val layoutResourceId: Int, private val viewModelClass: KClass<ViewModel>) :
        Fragment() {
    var baseActivity: BaseActivity<*, *>? = null
    var dataBinding: DataBinding? = null
    lateinit var viewModel: ViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.baseActivity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        dataBinding?.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProviders.of(this).get(viewModelClass.java)
        dataBinding?.setVariable(BR.viewModel, viewModel)
        return dataBinding?.root ?: inflater.inflate(layoutResourceId, container, false)
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }
}