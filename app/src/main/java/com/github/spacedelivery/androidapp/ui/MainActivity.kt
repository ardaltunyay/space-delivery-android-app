package com.github.spacedelivery.androidapp.ui

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.spacedelivery.androidapp.R
import com.github.spacedelivery.androidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    private val navigationInvisibleScreens = listOf(
        R.id.splashFragment,
        R.id.createSpaceVehicleFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.bnvMain.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener(this@MainActivity)
    }

    override fun onStop() {
        super.onStop()
        navController.removeOnDestinationChangedListener(this@MainActivity)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        with(binding.bnvMain) {
            visibility = when (destination.id) {
                in navigationInvisibleScreens -> View.GONE
                else -> View.VISIBLE
            }
        }
    }
}