package com.simonmcd.adapterbindings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simonmcd.adapterbindings.databinding.ActivityMainBinding
import com.simonmcd.adapterbindings.databinding.RowItemBinding
import com.simonmcd.adapterbindings.recyclerview.adapters.BindableRecyclerViewAdapter
import com.simonmcd.adapterbindings.recyclerview.adapters.BindableViewHolderListener

/**
 * A sample Activity to show how the AdapterBindings library works.
 */
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: BindableRecyclerViewAdapter<ItemRowViewModel, RowItemBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        adapter = BindableRecyclerViewAdapter(diffUtil, R.layout.row_item, viewHolderListener)
        viewModel.itemListData.observe(this, Observer { itemList ->
            adapter.submitList(itemList)
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private val viewHolderListener = object : BindableViewHolderListener<ItemRowViewModel, RowItemBinding> {
        override fun onCreated(viewBinding: RowItemBinding, viewHolder: RecyclerView.ViewHolder) {
            viewBinding.deleteButton.setOnClickListener {
                viewModel.removeItem(adapter.getBindableItem(viewHolder.adapterPosition))
            }
        }

        override fun rowIsClickable() = true

        override fun onRowClick(item: ItemRowViewModel) {
            val context = this@MainActivity
            val text = context.getString(R.string.click_item_text, item.displayText)
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<ItemRowViewModel>() {
        override fun areItemsTheSame(oldItem: ItemRowViewModel, newItem: ItemRowViewModel): Boolean {
            return oldItem.displayText == newItem.displayText
        }

        override fun areContentsTheSame(oldItem: ItemRowViewModel, newItem: ItemRowViewModel): Boolean {
            return oldItem.displayText == newItem.displayText
        }
    }
}
