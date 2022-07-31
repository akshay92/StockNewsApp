package com.akshay.stocknewsapp.main.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.stocknewsapp.main.core.domain.schedulers.DefaultSchedulers
import com.akshay.stocknewsapp.main.data.poll.StockPoller
import com.akshay.stocknewsapp.main.domain.model.NewsArticle
import com.akshay.stocknewsapp.main.domain.model.Stock
import com.akshay.stocknewsapp.main.domain.repository.StockRepository
import com.akshay.stocknewsapp.main.domain.usecase.GetNewsArticleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsArticleListUseCase: GetNewsArticleListUseCase,
    repository: StockRepository
) : ViewModel() {

    private var poller = StockPoller(
        repository,
        DefaultSchedulers().io()
    )

    private val _articles = MutableLiveData<List<NewsArticle>>()
    var articles: LiveData<List<NewsArticle>> = _articles

    private val _trendingArticles = MutableLiveData<List<NewsArticle>>()
    var trendingArticles: LiveData<List<NewsArticle>> = _articles

    private val _stocks = MutableLiveData<List<Stock>>()
    var stocks: LiveData<List<Stock>> = _stocks


    fun loadArticles() {
        getNewsArticleListUseCase.invoke {
            it.onSuccess {   data->
                _trendingArticles.postValue(data.subList(0,6))
                _articles.postValue(data.subList(6, data.size))
            }
        }
    }

    fun startPoller(timeDelay: Long = 1000) {
        viewModelScope.launch {
            poller.poll(timeDelay).collect {
                it.onSuccess { data ->
                    Log.v("Update Stock Price", "Update Stock Price")
                    _stocks.postValue(data)
                }
            }
        }
    }

    fun closePoller() {
        poller.close()
    }

}