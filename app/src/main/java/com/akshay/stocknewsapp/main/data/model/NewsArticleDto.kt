package com.akshay.stocknewsapp.main.data.model

import com.akshay.stocknewsapp.main.domain.model.NewsArticle
import com.squareup.moshi.Json

data class NewsArticleDto(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "urlToImage") val urlToImage: String,
    @field:Json(name = "publishedAt") val publishedAt: String
) {

    fun toNewArticle(): NewsArticle {
        return NewsArticle(
            title = title,
            description = description,
            urlToImage = urlToImage,
            publishedAt = publishedAt
        )
    }

}


