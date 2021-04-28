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
import com.karman.bluetoothcnc.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var dataBinding: FragmentHomeBinding? = null
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
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        dataBinding!!.viewModel = homeViewModel
        return dataBinding!!.root
    }

    override fun onDetach() {
        super.onDetach()
        homeActivity = null
    }
}