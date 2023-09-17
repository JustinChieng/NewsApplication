package com.justin.ui.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.justin.data.retrofit.NewsApi
import com.justin.newsapplication.R
import com.justin.newsapplication.databinding.FragmentDetailsBinding


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
        binding.tvNews.movementMethod = ScrollingMovementMethod()


        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

