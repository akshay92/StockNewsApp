package com.akshay.stocknewsapp.main.data.repository

import com.akshay.stocknewsapp.main.data.model.ArticleListResponseDto
import com.akshay.stocknewsapp.main.data.model.NewsArticleDto
import com.akshay.stocknewsapp.main.data.remote.NewsService
import com.akshay.stocknewsapp.main.domain.model.NewsArticle
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class NewsRepositoryImplTest {

    private lateinit var newsService: NewsService
    private lateinit var repositoryImpl : NewsRepositoryImpl
    @Before
    fun setup() {
        newsService = mockk()
        repositoryImpl = NewsRepositoryImpl(newsService)
    }

    @Test
    fun `when get response then mapping of response to article list `() = runTest {

        val testArticleList = ArrayList<NewsArticleDto>()
        testArticleList.add(NewsArticleDto("","","",""))
        testArticleList.add(NewsArticleDto("Test","Test","Test","Test"))
        val testResponse = ArticleListResponseDto(articleDtoList = testArticleList)

        coEvery { newsService.getNewsArticleList() } returns testResponse

        val actualResponse = repositoryImpl.getNewsArticleList()

        coVerify(exactly = 1) { newsService.getNewsArticleList()  }
        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse.isSuccess)
    }

}