package com.justin.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.justin.MyApplication
import com.justin.data.repo.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.justin.data.model.Result

class DetailsViewModel(
    private  val repo: NewsRepo
) : ViewModel() {
    val articles: MutableStateFlow<List<Result>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repo.getNews("top")
            articles.value = res.results
            Log.d("debugging", res.toString())

        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).newsRepo
                NewsFeedViewModel(
                    repo = myRepository,
                )
            }
        }
    }

}