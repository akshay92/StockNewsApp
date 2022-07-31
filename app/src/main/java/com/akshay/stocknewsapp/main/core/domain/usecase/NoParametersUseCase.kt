package com.akshay.stocknewsapp.main.core.domain.usecase

import com.akshay.stocknewsapp.main.core.domain.schedulers.DefaultSchedulers
import com.akshay.stocknewsapp.main.core.domain.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class NoParametersUseCase<Type>(
    schedulers: Schedulers = DefaultSchedulers()
) where Type : Any{

    private var backgroundContext : CoroutineContext = schedulers.io()
    private var foregroundContext : CoroutineContext = schedulers.main()

    abstract suspend fun run(): Result<Type>

    operator fun invoke(parentJob : Job = Job(), onResult:(Result<Type>) -> Unit = {}){
     val combinedScope = CoroutineScope(backgroundContext+parentJob)

     combinedScope.launch {
         val result = run()
         withContext(foregroundContext){
             onResult(result)
         }
     }
    }

}