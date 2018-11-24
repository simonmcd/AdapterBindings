package com.simonmcd.adapterbindings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.simonmcd.adapterbindings.databinding.ActivityMainBinding
import com.simonmcd.adapterbindings.recyclerview.adapters.BindableRecyclerViewAdapter

/**
 * A sample Activity to show how the AdapterBindings library works.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        val adapter = BindableRecyclerViewAdapter(diffUtil, R.layout.row_item, rowClickListener)
        viewModel.itemListData.observe(this, Observer { itemList ->
            adapter.submitList(itemList)
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private val rowClickListener = object : BindableRecyclerViewAdapter.RowClickListener<ItemRowViewModel> {
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
