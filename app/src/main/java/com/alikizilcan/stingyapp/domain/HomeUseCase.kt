package com.alikizilcan.stingyapp.domain

import com.alikizilcan.stingyapp.data.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: TransactionRepository
) {
    suspend fun getBudget(): Flow<Double>{
        return homeRepository.getBudget()
    }
}