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

}