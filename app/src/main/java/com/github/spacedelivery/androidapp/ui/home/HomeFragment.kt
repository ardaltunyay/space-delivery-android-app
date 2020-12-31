package com.github.spacedelivery.androidapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.github.spacedelivery.androidapp.R
import com.github.spacedelivery.androidapp.core.base.BaseFragment
import com.github.spacedelivery.androidapp.databinding.FragmentHomeBinding
import com.github.spacedelivery.androidapp.ui.home.adapter.SpaceStationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModel()

    private val spaceStationAdapter: SpaceStationAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SpaceStationAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.vpSpaceStation.adapter = spaceStationAdapter
        searchListener()

        viewModel.spaceStationListCombination.observe(viewLifecycleOwner) {
            spaceStationAdapter.updateList(it)
        }
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


}