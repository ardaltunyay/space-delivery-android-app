package com.github.spacedelivery.androidapp.ui.favourite_stations.adapter.viewholder


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.spacedelivery.androidapp.core.listeners.ItemClickListener
import com.github.spacedelivery.androidapp.databinding.ListItemFavouriteSpaceStationBinding
import com.github.spacedelivery.androidapp.ui.home.model.SpaceStationUIModel


class FavoriteSpaceStationHolder(private val binding: ListItemFavouriteSpaceStationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createHolder(parent: ViewGroup): FavoriteSpaceStationHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemFavouriteSpaceStationBinding.inflate(inflater, parent, false)
            return FavoriteSpaceStationHolder(binding)
        }
    }

    fun bind(
        spaceStationUIModel: SpaceStationUIModel,
        clickListener: ItemClickListener<SpaceStationUIModel>?
    ) {
        with(binding) {
            this.spaceStationUIModel = spaceStationUIModel
            this.clickListener = clickListener
            executePendingBindings()
        }
    }
}