package com.simonmcd.adapterbindings.recyclerview.adapters

import androidx.databinding.BaseObservable
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.simonmcd.adapterbindings.recyclerview.BR
import com.simonmcd.adapterbindings.recyclerview.R

/**
 * ViewHolder that uses databinding to populate the fields for a View.
 */
class BindableViewHolder<T : BaseObservable>(
    private val viewBinding: ViewDataBinding,
    clickListener: BindableRecyclerViewAdapter.RowClickListener<T>?
) : RecyclerView.ViewHolder(viewBinding.root) {

    private var boundItem: T? = null

    init {
        if (clickListener != null) {
            val attrs = intArrayOf(R.attr.selectableItemBackground)
            val typedArray = viewBinding.root.context.obtainStyledAttributes(attrs)
            val backgroundResource = typedArray.getResourceId(0, 0)
            viewBinding.root.setBackgroundResource(backgroundResource)
            typedArray.recycle()
            viewBinding.root.isClickable = true
            viewBinding.root.isFocusable = true
            viewBinding.root.setOnClickListener {
                boundItem?.let { item -> clickListener.onRowClick(item) }
            }
        }
    }

    /**
     * Bind [item] as the [BR.viewModel] to the Views.
     */
    fun bind(item: T) {
        boundItem = item
        viewBinding.setVariable(BR.viewModel, item)
        viewBinding.executePendingBindings()
    }
}