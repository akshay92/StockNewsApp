package com.akshay.stocknewsapp.main.di

import com.akshay.stocknewsapp.main.data.repository.NewsRepositoryImpl
import com.akshay.stocknewsapp.main.data.repository.StockRepositoryImpl
import com.akshay.stocknewsapp.main.domain.repository.NewsRepository
import com.akshay.stocknewsapp.main.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    abstract fun bindNewRepository(resp: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindStockRepository(resp: StockRepositoryImpl): StockRepository

}

