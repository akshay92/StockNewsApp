package com.akshay.stocknewsapp.main.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.akshay.stocknewsapp.databinding.ItemArticleTrendingBinding
import com.akshay.stocknewsapp.main.domain.model.NewsArticle

class TrendingArticleAdapter : ListAdapter<NewsArticle, TrendingArticleAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemArticleTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(item = currentList[position])
    }

    class ItemViewHolder(val binding: ItemArticleTrendingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsArticle) = with(binding) {
              data = item
            articleIm.load(item.urlToImage)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<NewsArticle>() {

        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem == newItem
        }
    }
}
