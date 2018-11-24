package com.simonmcd.adapterbindings.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BaseObservable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * An adapter that binds observable items to Views.
 */
class BindableRecyclerViewAdapter<T : BaseObservable>(
    diffCallback: DiffUtil.ItemCallback<T>, private val
    layoutResID: Int, private val clickListener: RowClickListener<T>? = null
) : ListAdapter<T, BindableViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, layoutResID, parent, false)
        return BindableViewHolder(viewBinding, clickListener)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Handles taps on adapter rows.
     */
    interface RowClickListener<ITEM> {

        /**
         * Fired when the user taps on a specific row and passes the [item] for that row.
         */
        fun onRowClick(item: ITEM)
    }
}