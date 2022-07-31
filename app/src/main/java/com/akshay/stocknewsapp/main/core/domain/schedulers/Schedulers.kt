package com.akshay.stocknewsapp.main.core.domain.schedulers

import kotlinx.coroutines.CoroutineDispatcher

interface Schedulers {
    fun io() : CoroutineDispatcher
    fun main() : CoroutineDispatcher
}