package com.karman.bluetoothcnc.view

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.karman.bluetoothcnc.R
import com.karman.bluetoothcnc.base.BaseActivity
import com.karman.bluetoothcnc.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>
(R.layout.activity_main, MainViewModel::class) {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(dataBinding?.toolbar?.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.homeFragment, R.id.operationListFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        dataBinding?.navView?.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment -> {
                    shouldShowBottomNavigation(true)
                }
                R.id.operationListFragment -> {
                    shouldShowBottomNavigation(true)
                }
            }
        }
    }

    /**
     * This method is used to show or hide bottom navigation view
     */
    fun shouldShowBottomNavigation(isShown: Boolean) {
        dataBinding?.navView?.visibility = if (isShown) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}