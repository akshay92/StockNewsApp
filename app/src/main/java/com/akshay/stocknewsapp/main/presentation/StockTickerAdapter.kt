package com.akshay.stocknewsapp.main.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akshay.stocknewsapp.databinding.ItemStockPriceBinding
import com.akshay.stocknewsapp.main.domain.model.Stock

class StockTickerAdapter :
    ListAdapter<Stock, StockTickerAdapter.ItemViewHolder>(StockTickerAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemStockPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(item = currentList[position])

    class ItemViewHolder(val binding: ItemStockPriceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Stock) = with(binding) {
            data = item
        }
    }

    private class StockTickerAdapterDiffCallback : DiffUtil.ItemCallback<Stock>() {

        override fun areItemsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return (oldItem.name == newItem.name) && (oldItem.price == newItem.price)
        }

        override fun areContentsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem == newItem
        }
    }
}

