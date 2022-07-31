package com.akshay.stocknewsapp.main.data.model

import com.squareup.moshi.Json

data class ArticleListResponseDto(
    @field:Json(name = "articles") val articleDtoList: List<NewsArticleDto>
    )