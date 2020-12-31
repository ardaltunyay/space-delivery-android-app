package com.github.spacedelivery.androidapp.ui.home.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.spacedelivery.androidapp.databinding.ListItemSpaceStationBinding
import com.github.spacedelivery.androidapp.ui.home.listener.ISpaceStationListener
import com.github.spacedelivery.androidapp.ui.home.model.SpaceStationUIModel

class SpaceStationHolder(private val binding: ListItemSpaceStationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createHolder(parent: ViewGroup): SpaceStationHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemSpaceStationBinding.inflate(inflater, parent, false)
            return SpaceStationHolder(binding)
        }
    }

    fun bind(
        spaceStationUIModel: SpaceStationUIModel,
        clickListener: ISpaceStationListener?
    ) {
        with(binding) {
            this.spaceStationUIModel = spaceStationUIModel
            this.clickListener = clickListener
            executePendingBindings()
        }
    }
}