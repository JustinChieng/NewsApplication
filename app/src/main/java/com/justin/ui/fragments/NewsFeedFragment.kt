package com.justin.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ProgressBar

import androidx.core.view.isVisible

import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.justin.MainActivity
import com.justin.newsapplication.R
import com.justin.newsapplication.databinding.FragmentNewsFeedBinding
import com.justin.ui.adapters.NewsAdapter
import com.justin.ui.viewModel.NewsFeedViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsFeedFragment() : Fragment() {

    private lateinit var binding: FragmentNewsFeedBinding
    val isEmpty: MutableLiveData<Boolean> = MutableLiveData(false)

    private val viewModel: NewsFeedViewModel by viewModels(
        ownerProducer = { requireActivity() as MainActivity },
        factoryProducer = { NewsFeedViewModel.Factory }
    )
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).showToolbar()

        setupAdapter()

        try {
            viewModel.articles.asLiveData().observe(viewLifecycleOwner) { results ->
                adapter.setNews(results)
            }

            viewModel.category.asLiveData().observe(viewLifecycleOwner) {
                Log.d("debugging", "hello")
                viewModel.FetchNews()
            }

            lifecycleScope.launch {
                viewModel.loading.collect {
                    binding.flLoading.isVisible = it
                }
            }

            adapter.setOnItemClickListener { results ->
                val action =
                    results.image_url?.let {
                        NewsFeedFragmentDirections.actionNewsFeedFragment4ToDetailsFragment(
                            imageUrl = it,
                            articleTitle = results.title,
                            articleDate = results.pubDate,
                            articleContent = results.content
                        )
                    }
                if (action != null) {
                    findNavController().navigate(action)
                }
            }
        } catch (e: Exception) {
            Log.e("NewsFeedFragment", "Error loading news: ${e.message}")
            Snackbar.make(view, "Error loading news. Please refresh the app.", Snackbar.LENGTH_LONG).show()
        }

        binding.btnLoadMore.setOnClickListener {
            viewModel.loadMore()
        }
    }

    fun setupAdapter() {
        adapter = NewsAdapter(requireContext(), emptyList()) { results ->

            val action =
                results.image_url?.let {
                    NewsFeedFragmentDirections.actionNewsFeedFragment4ToDetailsFragment(
                        articleTitle = results.title,
                        imageUrl = it,
                        articleDate = results.pubDate,
                        articleContent = results.content
                    )
                }
            if (action != null) {
                findNavController().navigate(action)
            }
        }
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecyclerView.adapter = adapter
        binding.rvRecyclerView.layoutManager = layoutManager
    }
}
