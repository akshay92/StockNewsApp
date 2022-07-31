package com.akshay.stocknewsapp.main.domain.usecase

import com.akshay.stocknewsapp.main.domain.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetNewsArticleListUseCaseTest {

     private val repository : NewsRepository = mockk()

    @Test
    fun `when run getNewsArticleList then get success result`() = runTest{

        val useCase = GetNewsArticleListUseCase(repository)
        coEvery { repository.getNewsArticleList() } returns Result.success(ArrayList())

        val actualResponse = useCase.run()

        coVerify(exactly = 1) {
            repository.getNewsArticleList()
        }
        Assert.assertNotNull(actualResponse)
    }

    @Test
    fun `when run getNewsArticleList then get failure result`() = runTest{

        val useCase = GetNewsArticleListUseCase(repository)
        coEvery { repository.getNewsArticleList() } returns Result.failure(Exception())

        val actualResponse = useCase.run()

        coVerify(exactly = 1) {
            repository.getNewsArticleList()
        }
        Assert.assertNotNull(actualResponse)
    }

 }