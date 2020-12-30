package com.github.spacedelivery.androidapp.ui.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.github.spacedelivery.androidapp.R
import com.github.spacedelivery.androidapp.core.base.BaseFragment
import com.github.spacedelivery.androidapp.databinding.FragmentSplashBinding
import com.github.spacedelivery.androidapp.ui.splash.model.SplashActionState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment :
    BaseFragment<SplashViewModel, FragmentSplashBinding>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.actionState.observe(viewLifecycleOwner) { actionState ->
            when (actionState) {
                is SplashActionState.HasSpaceVehicle -> {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                }
                is SplashActionState.NoHaveSpaceVehicle -> {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToCreateSpaceVehicleFragment())
                }
            }
        }
    }

}