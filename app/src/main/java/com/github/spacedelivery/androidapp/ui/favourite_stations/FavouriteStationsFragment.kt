package com.github.spacedelivery.androidapp.ui.favourite_stations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.spacedelivery.androidapp.R

class FavouriteStationsFragment : Fragment() {

    companion object {
        fun newInstance() = FavouriteStationsFragment()
    }

    private lateinit var viewModel: FavouriteStationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite_stations, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavouriteStationsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}