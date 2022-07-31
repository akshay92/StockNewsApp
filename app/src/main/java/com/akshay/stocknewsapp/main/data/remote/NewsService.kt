package com.akshay.stocknewsapp.main.data.remote

import com.akshay.stocknewsapp.main.data.model.ArticleListResponseDto
import retrofit2.http.GET

interface NewsService {

    @GET("/NewsAPI/everything/cnn.json")
    suspend fun getNewsArticleList(): ArticleListResponseDto
}