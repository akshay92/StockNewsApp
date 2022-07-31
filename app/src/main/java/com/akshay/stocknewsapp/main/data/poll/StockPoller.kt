package com.akshay.stocknewsapp.main.data.poll

import com.akshay.stocknewsapp.main.domain.model.Stock
import com.akshay.stocknewsapp.main.domain.repository.StockRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

class StockPoller(
    private val repository: StockRepository,
    private val dispatcher: CoroutineDispatcher
) : Poller {

    private var isCanceledPool : Boolean = false

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun poll(delay: Long): Flow<Result<List<Stock>>> {
        isCanceledPool = false
        return channelFlow {
            while (!isClosedForSend) {
                if(isCanceledPool){
                    close()
                }
                val data = repository.getStockList()
                send(data)
                delay(delay)
            }
        }.flowOn(dispatcher)
    }

    override fun close() {
       isCanceledPool = true
    }
}