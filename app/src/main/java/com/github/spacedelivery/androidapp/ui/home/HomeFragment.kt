package com.github.spacedelivery.androidapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import com.github.spacedelivery.androidapp.R
import com.github.spacedelivery.androidapp.core.base.BaseFragment
import com.github.spacedelivery.androidapp.core.extensions.toast
import com.github.spacedelivery.androidapp.databinding.FragmentHomeBinding
import com.github.spacedelivery.androidapp.ui.home.adapter.SpaceStationAdapter
import com.github.spacedelivery.androidapp.ui.home.listener.ISpaceStationListener
import com.github.spacedelivery.androidapp.ui.home.model.SpaceStationUIModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home),
    ISpaceStationListener {

    override val viewModel: HomeViewModel by viewModel()

    private val spaceStationAdapter: SpaceStationAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SpaceStationAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setSpaceStationAdapter()
        searchListener()

        viewModel.spaceStationListCombination.observe(viewLifecycleOwner) {
            spaceStationAdapter.updateList(it)
        }

        viewModel.completeState.observe(viewLifecycleOwner) {
            showComplete()
        }
    }

    private fun showComplete() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setMessage("Success!! Welcome to world!")
        }.show()
    }

    private fun setSpaceStationAdapter() {
        binding.vpSpaceStation.adapter = spaceStationAdapter
        spaceStationAdapter.setClickListener(this@HomeFragment)
    }

    private fun searchListener() {
        binding.svSpaceStation.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                p0?.let {
                    val oldQueryText = viewModel.queryText.value
                    if (oldQueryText != it) {
                        viewModel.queryText.value = it
                    }
                }
                return false
            }

        })
    }

    override fun onTravelClicked(spaceStationUIModel: SpaceStationUIModel) {
        lifecycleScope.launch {
            toast(R.string.home_starting_travel_text)
            delay(1000)
            viewModel.getTravel(spaceStationUIModel)
        }
    }

    override fun onFavoriteClicked(spaceStationUIModel: SpaceStationUIModel) {
        viewModel.toggleFavorite(spaceStationUIModel)
    }


}