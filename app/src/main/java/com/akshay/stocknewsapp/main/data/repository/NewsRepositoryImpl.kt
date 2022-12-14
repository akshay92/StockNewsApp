package com.akshay.stocknewsapp.main.data.repository

import com.akshay.stocknewsapp.main.data.remote.NewsService
import com.akshay.stocknewsapp.main.domain.model.NewsArticle
import com.akshay.stocknewsapp.main.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : NewsRepository {

    override suspend fun getNewsArticleList(): Result<List<NewsArticle>> {
        return runCatching { newsService.getNewsArticleList().articleDtoList.map { it.toNewArticle() } }
            .onSuccess {
                Result.success(it)
            }.onFailure {
                Result.failure<Throwable>(it)
            }
    }
}