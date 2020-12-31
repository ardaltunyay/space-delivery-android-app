package com.github.spacedelivery.androidapp.ui.favourite_stations.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.spacedelivery.androidapp.core.listeners.ItemClickListener
import com.github.spacedelivery.androidapp.ui.favourite_stations.adapter.viewholder.FavoriteSpaceStationHolder
import com.github.spacedelivery.androidapp.ui.home.model.SpaceStationUIModel

class FavoriteSpaceStationAdapter : RecyclerView.Adapter<FavoriteSpaceStationHolder>() {

    private val _spaceStations = mutableListOf<SpaceStationUIModel>()

    private var _clickListener: ItemClickListener<SpaceStationUIModel>? = null

    fun updateList(spacesStations: List<SpaceStationUIModel>) {
        _spaceStations.clear()
        _spaceStations.addAll(spacesStations)
        notifyDataSetChanged()
    }

    fun setClickListener(clickListener: ItemClickListener<SpaceStationUIModel>) {
        _clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteSpaceStationHolder =
        FavoriteSpaceStationHolder.createHolder(parent)

    override fun onBindViewHolder(holder: FavoriteSpaceStationHolder, position: Int) {
        holder.bind(_spaceStations[position], _clickListener)
    }

    override fun getItemCount(): Int = _spaceStations.size

}