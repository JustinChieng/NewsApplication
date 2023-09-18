package com.justin.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.justin.MyApplication
import com.justin.data.model.Result
import com.justin.data.repo.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsFeedViewModel(
    private  val repo: NewsRepo
) : ViewModel() {
    val articles: MutableStateFlow<List<Result>> = MutableStateFlow(emptyList())
    private val _category: MutableStateFlow<String> = MutableStateFlow("top")
    val category: StateFlow<String> = _category

    private val _loading: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val loading: SharedFlow<Boolean> = _loading
    private var nextPage = ""

    init {

        FetchNews()

    }

    fun setCategory(cat: String) {
        Log.d("debugging", "Hello set")
        _category.value = cat
    }

    fun FetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.emit(true)
            val res = repo.getNews(category.value)
            _loading.emit(false)
            articles.value = res.results
            nextPage = res.nextPage
            Log.d("debugging", res.toString())

        }
    }

    fun loadMore() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.emit(true)
            val res = repo.getNews(category = category.value, nextPage = nextPage)
            _loading.emit(false)
            articles.value = articles.value + res.results
            nextPage = res.nextPage
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