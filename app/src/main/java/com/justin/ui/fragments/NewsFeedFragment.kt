package com.justin.ui.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.justin.MainActivity
import com.justin.newsapplication.databinding.FragmentNewsFeedBinding
import com.justin.ui.adapters.NewsAdapter
import com.justin.ui.viewModel.NewsFeedViewModel


class NewsFeedFragment() : Fragment() {

    private lateinit var binding: FragmentNewsFeedBinding
    val isEmpty: MutableLiveData<Boolean> = MutableLiveData(false)

    private val viewModel: NewsFeedViewModel by viewModels(
        ownerProducer = { requireActivity() as MainActivity },
        factoryProducer = { NewsFeedViewModel.Factory  }
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

//        viewModel.articles.asLiveData().observe(viewLifecycleOwner) {
//            adapter.setTasks(it)
//        }

        viewModel.articles.asLiveData().observe(viewLifecycleOwner) { articles ->
            adapter.setNews(articles)
        }

        viewModel.category.asLiveData().observe(viewLifecycleOwner) {
            Log.d("debugging", "hello")
            viewModel.FetchNews()
        }


        adapter.setOnItemClickListener { article ->
            val action =
                NewsFeedFragmentDirections.actionNewsFeedFragment4ToDetailsFragment(
                    imageUrl = article.urlToImage,
                    articleContent = article.content
                )
            findNavController().navigate(action)
        }
    }



    fun setupAdapter() {
        adapter = NewsAdapter(requireContext(), emptyList()) { article ->

            val action =
                NewsFeedFragmentDirections.actionNewsFeedFragment4ToDetailsFragment(
                    imageUrl = article.urlToImage,
                    articleContent = article.content
                )
            findNavController().navigate(action)
        }
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecyclerView.adapter = adapter
        binding.rvRecyclerView.layoutManager = layoutManager


    }



}

