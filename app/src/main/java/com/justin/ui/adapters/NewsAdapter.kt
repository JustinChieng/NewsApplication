package com.justin.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.justin.data.model.Result
import com.justin.newsapplication.R
import com.justin.newsapplication.databinding.NewsFeedListItemsBinding

class NewsAdapter(
    private val context: Context,
    private var news: List<Result>,
    private var onItemClick: (Result) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: NewsFeedListItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsFeedListItemsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val new = news[position]
        val imageUrl = new.image_url
        
        holder.binding.tvTitle.text = new.title
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(holder.binding.ivImage)


        holder.itemView.setOnClickListener {
            onItemClick(new)
        }
    }

    fun setNews(news: List<Result>) {
        this.news = news
        notifyDataSetChanged()
    }


    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClick = listener
    }
}
