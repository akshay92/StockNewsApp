package com.akshay.stocknewsapp.main.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.akshay.stocknewsapp.main.data.model.ArticleListResponseDto
import com.akshay.stocknewsapp.main.data.model.NewsArticleDto
import com.akshay.stocknewsapp.main.data.remote.NewsService
import com.akshay.stocknewsapp.main.data.repository.NewsRepositoryImpl
import com.akshay.stocknewsapp.main.data.repository.StockRepositoryImpl
import com.akshay.stocknewsapp.main.domain.model.NewsArticle
import com.akshay.stocknewsapp.main.domain.repository.NewsRepository
import com.akshay.stocknewsapp.main.domain.repository.StockRepository
import com.akshay.stocknewsapp.main.domain.usecase.GetNewsArticleListUseCase
import com.akshay.stocknewsapp.util.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.*
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    lateinit var viewModel: MainViewModel
    lateinit var repository: StockRepository
    var newsService: NewsService = mockk()
    var newRepository: NewsRepository = NewsRepositoryImpl(newsService)
    var getNewsArticleListUseCase: GetNewsArticleListUseCase =
        GetNewsArticleListUseCase(newRepository)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = StockRepositoryImpl(mockk(), mockk())
        viewModel = MainViewModel(getNewsArticleListUseCase, repository)
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
    }


    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    @Test
    fun `when load article then article trendingarticle update`() = runTest {
        val testArticleList = ArrayList<NewsArticleDto>()
        testArticleList.add(NewsArticleDto("", "", "", ""))
        testArticleList.add(NewsArticleDto("Test", "Test", "Test", "Test"))
        testArticleList.add(NewsArticleDto("", "", "", ""))
        testArticleList.add(NewsArticleDto("Test", "Test", "Test", "Test"))
        testArticleList.add(NewsArticleDto("", "", "", ""))
        testArticleList.add(NewsArticleDto("Test", "Test", "Test", "Test"))
        testArticleList.add(NewsArticleDto("", "", "", ""))
        testArticleList.add(NewsArticleDto("Test", "Test", "Test", "Test"))


        val testResponse = ArticleListResponseDto(articleDtoList = testArticleList)

        coEvery { newsService.getNewsArticleList() } returns testResponse

        viewModel.loadArticles()
        delay(1000)
        val actualResponse = viewModel.trendingArticles.getOrAwaitValue()
        Assert.assertNotNull(actualResponse)
    }
}
