package com.akshay.stocknewsapp.main.domain.usecase

import com.akshay.stocknewsapp.main.core.domain.usecase.NoParametersUseCase
import com.akshay.stocknewsapp.main.domain.model.NewsArticle
import com.akshay.stocknewsapp.main.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsArticleListUseCase @Inject constructor(private val repository: NewsRepository) :
    NoParametersUseCase<List<NewsArticle>>() {

    override suspend fun run(): Result<List<NewsArticle>> = repository.getNewsArticleList()
}