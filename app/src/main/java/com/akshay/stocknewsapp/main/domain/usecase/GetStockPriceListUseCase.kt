package com.akshay.stocknewsapp.main.domain.usecase

import com.akshay.stocknewsapp.main.core.domain.usecase.NoParametersUseCase
import com.akshay.stocknewsapp.main.domain.model.Stock
import com.akshay.stocknewsapp.main.domain.repository.StockRepository
import javax.inject.Inject

class GetStockPriceListUseCase @Inject constructor(private val repository: StockRepository) :
    NoParametersUseCase<List<Stock>>() {

    override suspend fun run(): Result<List<Stock>> = repository.getStockList()

}