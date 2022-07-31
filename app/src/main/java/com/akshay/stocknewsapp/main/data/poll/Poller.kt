package com.akshay.stocknewsapp.main.data.poll

import com.akshay.stocknewsapp.main.domain.model.Stock
import kotlinx.coroutines.flow.Flow

interface Poller {
    fun poll(delay: Long): Flow<Result<List<Stock>>>
    fun close()
}