package com.akshay.stocknewsapp.main.di

import android.content.Context
import com.akshay.stocknewsapp.main.data.remote.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return  Retrofit.Builder()
            .baseUrl("https://saurav.tech/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(NewsService::class.java)
    }

}