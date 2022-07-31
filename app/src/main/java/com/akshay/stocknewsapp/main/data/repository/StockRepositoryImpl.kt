package com.akshay.stocknewsapp.main.data.repository

import android.content.Context
import com.akshay.stocknewsapp.R
import com.akshay.stocknewsapp.main.common.CsvFileReader
import com.akshay.stocknewsapp.main.domain.model.Stock
import com.akshay.stocknewsapp.main.domain.repository.StockRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val csvFileReader: CsvFileReader,
    @ApplicationContext private val context: Context
) : StockRepository {

    override suspend fun getStockList(): Result<List<Stock>> {
        return kotlin.runCatching {
            csvFileReader.read(context.resources.openRawResource(R.raw.stocks)).map {
                Stock(it.key, it.value.random())
            }
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<Throwable>(it)
        }
    }
}