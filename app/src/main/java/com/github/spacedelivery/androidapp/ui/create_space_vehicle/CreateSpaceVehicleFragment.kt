package com.github.spacedelivery.androidapp.ui.create_space_vehicle

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.spacedelivery.androidapp.R

class CreateSpaceVehicleFragment : Fragment() {

    companion object {
        fun newInstance() = CreateSpaceVehicleFragment()
    }

    private lateinit var viewModel: CreateSpaceVehicleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_space_vehicle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateSpaceVehicleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}