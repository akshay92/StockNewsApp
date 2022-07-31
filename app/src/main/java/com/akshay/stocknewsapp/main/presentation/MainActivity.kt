package com.akshay.stocknewsapp.main.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import com.akshay.stocknewsapp.databinding.ActvityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActvityMainBinding

    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var trendingArticleAdapterHorizontalContainerAdapter: HorizontalContainerAdapter
    private lateinit var stockHorizontalContainerAdapter: HorizontalContainerAdapter
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var stockAdapter: StockTickerAdapter
    private lateinit var trendingArticleAdapter: TrendingArticleAdapter

    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActvityMainBinding.inflate(layoutInflater).also { binding = it }
        setContentView(binding.root)

        initializeAdapter()

        setUpObserver()

        mainViewModel.startPoller()
        mainViewModel.loadArticles()
    }

    private fun setUpObserver() {
        with(mainViewModel){
            stocks.observe(this@MainActivity, stockAdapter::submitList)
            articles.observe(this@MainActivity, articleAdapter::submitList)
            trendingArticles.observe(this@MainActivity, trendingArticleAdapter::submitList)
        }
    }

    private fun initializeAdapter() {
        stockAdapter = StockTickerAdapter()
        articleAdapter = ArticleAdapter()
        trendingArticleAdapter = TrendingArticleAdapter()
        trendingArticleAdapterHorizontalContainerAdapter =
            HorizontalContainerAdapter(this, trendingArticleAdapter)
        stockHorizontalContainerAdapter = HorizontalContainerAdapter(this, stockAdapter)

        concatAdapter = ConcatAdapter(
            stockHorizontalContainerAdapter,
            trendingArticleAdapterHorizontalContainerAdapter,
            articleAdapter
        )
        binding.mainRecyclerView.adapter = concatAdapter
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.closePoller()
    }

    override fun onPause() {
        super.onPause()
        mainViewModel.closePoller()
    }

    override fun onRestart() {
        super.onRestart()
        mainViewModel.startPoller()
    }

}
