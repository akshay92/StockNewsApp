package com.akshay.stocknewsapp.main.domain.repository

import com.akshay.stocknewsapp.main.domain.model.Stock

interface StockRepository {
    suspend fun getStockList() : Result<List<Stock>>
}