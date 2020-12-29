package com.github.spacedelivery.androidapp.core.listeners

class ItemClickListener<T>(private val click: (T) -> Unit) {
    fun onClicked(data: T) = click(data)
}