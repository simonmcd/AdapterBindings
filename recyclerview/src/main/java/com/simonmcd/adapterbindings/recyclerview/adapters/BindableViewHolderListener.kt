package com.simonmcd.adapterbindings.recyclerview.adapters

import androidx.databinding.BaseObservable
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * A listener that fires on view holder events.
 */
interface BindableViewHolderListener<ITEM : BaseObservable, VIEW_BINDING : ViewDataBinding> {

    /**
     * Fired when the ViewHolder is created. Can be used to add extra listeners.
     */
    fun onCreated(viewBinding: VIEW_BINDING, viewHolder: RecyclerView.ViewHolder)

    /**
     * Fired when the user taps on a specific row and passes the [item] for that row.
     */
    fun onRowClick(item: ITEM)

    /**
     * Returns true if the row is clickable or false if it is not.
     */
    fun rowIsClickable(): Boolean
}