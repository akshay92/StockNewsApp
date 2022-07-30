package com.akshay.stocknewsapp.main.data.remote

import com.akshay.stocknewsapp.main.data.model.NewsArticleDto
import retrofit2.http.GET

interface NewsService {

    @GET("/NewsAPI/everything/cnn.json")
    fun getNewsArticleList(): List<NewsArticleDto>
}