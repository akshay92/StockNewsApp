package com.akshay.stocknewsapp.main.presentation

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

    private val TRENDING_ARTICLE_SIZE = 6

    private var poller = StockPoller(
        repository,
        DefaultSchedulers().io()
    )

    private val _articles = MutableLiveData<List<NewsArticle>>()
    var articles: LiveData<List<NewsArticle>> = _articles

    private val _trendingArticles = MutableLiveData<List<NewsArticle>>()
    var trendingArticles: LiveData<List<NewsArticle>> = _trendingArticles

    private val _stocks = MutableLiveData<List<Stock>>()
    var stocks: LiveData<List<Stock>> = _stocks


    fun loadArticles() {
        getNewsArticleListUseCase.invoke {
            it.onSuccess { data ->
                _trendingArticles.postValue(data.getTrendingList())
                _articles.postValue(data.getArticleList())
            }
        }
    }

    fun startPoller(timeDelay: Long = 1000) {
        viewModelScope.launch {
            poller.poll(timeDelay).collect {
                it.onSuccess { data ->
                    _stocks.postValue(data)
                }
            }
        }
    }

    fun closePoller() {
        poller.close()
    }


    private fun List<NewsArticle>.getTrendingList(): List<NewsArticle> =  if (size <= TRENDING_ARTICLE_SIZE) {
            this
        } else {
            subList(0, TRENDING_ARTICLE_SIZE)
        }

    private fun List<NewsArticle>.getArticleList(): List<NewsArticle> =  if (size > TRENDING_ARTICLE_SIZE) {
            subList(TRENDING_ARTICLE_SIZE, size)
        } else {
            emptyList()
        }

}