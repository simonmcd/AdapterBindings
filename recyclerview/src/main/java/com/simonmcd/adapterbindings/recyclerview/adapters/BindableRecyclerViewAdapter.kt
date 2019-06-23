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
class BindableRecyclerViewAdapter<T : BaseObservable, VIEW_BINDING : ViewDataBinding>(
    diffCallback: DiffUtil.ItemCallback<T>, private val
    layoutResID: Int, private val viewHolderListener: BindableViewHolderListener<T, VIEW_BINDING>? = null
) : ListAdapter<T, BindableViewHolder<T, VIEW_BINDING>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T, VIEW_BINDING> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding: VIEW_BINDING = DataBindingUtil.inflate(layoutInflater, layoutResID, parent, false)
        return BindableViewHolder(viewBinding, viewHolderListener)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<T, VIEW_BINDING>, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * The item at the specified [position].
     */
    fun getBindableItem(position: Int): T = getItem(position)
}