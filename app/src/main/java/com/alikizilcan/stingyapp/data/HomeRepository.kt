package com.alikizilcan.stingyapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val stingyLocalDataSource: StingyLocalDataSource
) {

    suspend fun getBudget(): Flow<Double> = flow {
        emit(stingyLocalDataSource.getBudget())
    }

    suspend fun getTotalTransactionByCategory() : Flow<Map<String,Double>> = flow{
        emit(stingyLocalDataSource.getTotalTransactionByCategory())
    }
}