package com.simonmcd.adapterbindings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Main ViewModel for controlling the list displayed in [MainActivity].
 */
class MainViewModel : ViewModel() {

    private var counter = 0

    val itemListData = MutableLiveData<List<ItemRowViewModel>>()

    fun addItem() {
        val list = getItemList()
        counter++
        list.add(ItemRowViewModel(Item(counter), this::removeItem))
        itemListData.value = list
    }

    private fun removeItem(item: ItemRowViewModel) {
        val list = getItemList()
        list.remove(item)
        itemListData.value = list
    }

    private fun getItemList() = itemListData.value?.toMutableList() ?: mutableListOf()
}