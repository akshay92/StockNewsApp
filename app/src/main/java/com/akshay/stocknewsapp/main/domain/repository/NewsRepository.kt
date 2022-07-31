package com.akshay.stocknewsapp.main.domain.repository

import com.akshay.stocknewsapp.main.domain.model.NewsArticle

interface NewsRepository {
    suspend fun getNewsArticleList() : Result<List<NewsArticle>>
}