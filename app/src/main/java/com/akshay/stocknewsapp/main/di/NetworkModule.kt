package com.akshay.stocknewsapp.main.di

import com.akshay.stocknewsapp.main.data.remote.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return  Retrofit.Builder()
            .baseUrl("https://saurav.tech")  // We should get it from build config
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(NewsService::class.java)
    }

}