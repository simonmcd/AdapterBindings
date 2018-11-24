package com.simonmcd.adapterbindings

import androidx.databinding.BaseObservable

/**
 * View model for binding the [Item] to a View. When the delete button is tapped the [removeListener] is invoked.
 */
class ItemRowViewModel(item: Item, private val removeListener: (ItemRowViewModel) -> Unit) : BaseObservable() {

    val displayText = item.number.toString()

    fun remove() {
        removeListener(this)
    }
}