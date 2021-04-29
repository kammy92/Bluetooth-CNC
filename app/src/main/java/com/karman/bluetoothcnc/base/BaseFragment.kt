package com.karman.bluetoothcnc.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.karman.bluetoothcnc.HomeActivity
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.databinding.FragmentHomeBinding
import com.karman.bluetoothcnc.view.home.HomeViewModel

class BaseFragment<MyDataBinding : ViewDataBinding, MyViewModel : ViewModel> : Fragment() {

    var viewDataBinding: MyDataBinding? = null
        private set
    private var mViewModel: MyViewModel? = null

//    private lateinit var viewModel:T
//    private var dataBinding: FragmentHomeBinding? = null
//    private var homeActivity: HomeActivity? = null
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        homeActivity = context as HomeActivity
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        dataBinding =
//            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
//        viewModel = ViewModelProviders.of(this).get(T::class.java)
//        dataBinding!!.viewModel = viewModel
//        return dataBinding!!.root
//    }

//    override fun onDetach() {
//        super.onDetach()
//        homeActivity = null
//    }
}