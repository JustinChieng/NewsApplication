package com.justin.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.justin.data.model.Article
import com.justin.newsapplication.R
import com.justin.newsapplication.databinding.NewsFeedListItemsBinding

class NewsAdapter(
    private val context: Context,
    private var news: List<Article>,
    private var onItemClick: (Article) -> Unit
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
        val imageUrl = new.urlToImage
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

    fun setNews(news: List<Article>) {
        this.news = news
        notifyDataSetChanged()
    }


    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClick = listener
    }
}
