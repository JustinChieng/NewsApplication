package com.justin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.justin.data.model.NewsModel
import com.justin.data.retrofit.NewsApi
import com.justin.newsapplication.R
import com.justin.newsapplication.databinding.FragmentDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var apiService: NewsApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(NewsApi::class.java)

        val call = apiService.getNews()
        call.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val textContent = apiResponse?.content ?: "No content available"
                    val tvNews = findViewById<TextView>(R.id.tvNews)
                    tvNews.text = textContent
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the imageUrl and articleContent from navigation arguments
        val imageUrl = args.imageUrl
        val articleContent = args.articleContent

        // Load the image using Glide
        Glide.with(requireContext())
            .load(imageUrl)
            .placeholder(R.drawable.ic_img)
            .error(R.drawable.error_image)
            .into(binding.ivImage)

        // Set the article content to the TextView
        binding.tvNews.text = articleContent

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

