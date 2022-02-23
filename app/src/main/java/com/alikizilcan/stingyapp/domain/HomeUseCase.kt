package com.alikizilcan.stingyapp.domain

import com.alikizilcan.stingyapp.data.HomeRepository
import com.alikizilcan.stingyapp.domain.mapper.DataToPieEntry
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val dataToPieEntry: DataToPieEntry
) {
    suspend fun getBudget(): Flow<Double?>{
        return homeRepository.getBudget()
    }

    suspend fun getTotalTransactionByCategory() : Flow<ArrayList<PieEntry>> {
        return homeRepository.getTotalTransactionByCategory().map {
            dataToPieEntry.mapFromData(it)
        }
    }

    suspend fun getTotalTransactions() : Flow<Map<String,Double>>{
        return homeRepository.getTotalTransactions()
    }

    suspend fun insertBudgetFirst() = homeRepository.insertBudgetFirst()
}