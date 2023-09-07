package com.kirenraj.newsappexp.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kirenraj.newsappexp.R
import com.kirenraj.newsappexp.data.model.Article
import com.kirenraj.newsappexp.databinding.NewsFeedListItemsBinding

class NewsAdapter(
    private val context: Context,
    private var news: List<Article>,
): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: NewsFeedListItemsBinding): RecyclerView.ViewHolder(binding.root)

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

        Log.d("ImageURL", imageUrl.toString())


    }

    fun setTasks(news: List<Article>) {
        this.news = news
        notifyDataSetChanged()
    }
}