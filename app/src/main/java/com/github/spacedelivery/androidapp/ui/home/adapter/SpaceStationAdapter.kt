package com.github.spacedelivery.androidapp.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.spacedelivery.androidapp.ui.home.adapter.viewholder.SpaceStationHolder
import com.github.spacedelivery.androidapp.ui.home.listener.ISpaceStationListener
import com.github.spacedelivery.androidapp.ui.home.model.SpaceStationUIModel

class SpaceStationAdapter : RecyclerView.Adapter<SpaceStationHolder>() {

    private val _spaceStations = mutableListOf<SpaceStationUIModel>()

    private var _clickListener: ISpaceStationListener? = null

    fun updateList(spacesStations: List<SpaceStationUIModel>) {
        _spaceStations.clear()
        _spaceStations.addAll(spacesStations)
        notifyDataSetChanged()
    }

    fun setClickListener(clickListener: ISpaceStationListener) {
        _clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpaceStationHolder =
        SpaceStationHolder.createHolder(parent)

    override fun onBindViewHolder(holder: SpaceStationHolder, position: Int) {
        holder.bind(_spaceStations[position], _clickListener)
    }

    override fun getItemCount(): Int = _spaceStations.size

}