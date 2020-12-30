package com.github.spacedelivery.androidapp.ui.create_space_vehicle

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.github.spacedelivery.androidapp.R
import com.github.spacedelivery.androidapp.core.base.BaseFragment
import com.github.spacedelivery.androidapp.core.extensions.toast
import com.github.spacedelivery.androidapp.databinding.FragmentCreateSpaceVehicleBinding
import com.github.spacedelivery.androidapp.ui.create_space_vehicle.model.CreateSpaceVehicleActionState
import com.google.android.material.slider.Slider
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateSpaceVehicleFragment :
    BaseFragment<CreateSpaceVehicleViewModel, FragmentCreateSpaceVehicleBinding>(R.layout.fragment_create_space_vehicle) {

    override val viewModel: CreateSpaceVehicleViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        initListeners()

        viewModel.actionState.observe(viewLifecycleOwner) { actionState ->
            when (actionState) {
                is CreateSpaceVehicleActionState.CreatedSpaceVehicleState -> {
                    toast(R.string.welcome, Toast.LENGTH_LONG)
                    findNavController().navigate(CreateSpaceVehicleFragmentDirections.actionCreateSpaceVehicleFragmentToHomeFragment())
                }
            }
        }

    }

    private fun initListeners() {
        binding.slStrength.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            viewModel.updateStrength(value.toInt())
        })

        binding.slSpeed.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            viewModel.updateSpeed(value.toInt())
        })

        binding.slCapacity.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            viewModel.updateCapacity(value.toInt())
        })
    }

}