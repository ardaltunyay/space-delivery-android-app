package com.github.spacedelivery.androidapp.ui.favourite_stations

import android.os.Bundle
import android.view.View
import com.github.spacedelivery.androidapp.R
import com.github.spacedelivery.androidapp.core.base.BaseFragment
import com.github.spacedelivery.androidapp.core.listeners.ItemClickListener
import com.github.spacedelivery.androidapp.databinding.FragmentFavouriteStationsBinding
import com.github.spacedelivery.androidapp.ui.favourite_stations.adapter.FavoriteSpaceStationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteStationsFragment :
    BaseFragment<FavouriteStationsViewModel, FragmentFavouriteStationsBinding>(R.layout.fragment_favourite_stations) {

    private val favoriteSpaceStationAdapter: FavoriteSpaceStationAdapter by lazy(
        LazyThreadSafetyMode.NONE
    ) {
        FavoriteSpaceStationAdapter()
    }

    override val viewModel: FavouriteStationsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setFavoriteSpaceStationAdapter()

        viewModel.favoriteSpaceStations.observe(viewLifecycleOwner) { list ->
            favoriteSpaceStationAdapter.updateList(list)
        }
    }

    private fun setFavoriteSpaceStationAdapter() {
        binding.rvFavoriteSpaceStation.adapter = favoriteSpaceStationAdapter
        favoriteSpaceStationAdapter.setClickListener(ItemClickListener {
            viewModel.toggleFavoriteSpaceStation(it)
        })
    }


}