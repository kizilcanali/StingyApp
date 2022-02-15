package com.alikizilcan.stingyapp.data

import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val stingyLocalDataSource: StingyLocalDataSource) {


    fun fetchTransactions(): Flow<List<TransactionsEntity>> = flow {
        emit(stingyLocalDataSource.fetchTransactions())
    }

    suspend fun insertTransactions(transactionsEntity: TransactionsEntity) =
        stingyLocalDataSource.insertTransaction(transactionsEntity)

    suspend fun deleteTransaction(transactionsEntity: TransactionsEntity) =
        stingyLocalDataSource.deleteTransaction(transactionsEntity)

    suspend fun updateBudget(newBudget: Double) = stingyLocalDataSource.updateBudget(newBudget)

    suspend fun getBudget(): Flow<Double> = flow {
        emit(stingyLocalDataSource.getBudget())
    }

    suspend fun insertInstallment(installment: InstallmentsEntity) =
        stingyLocalDataSource.insertInstallment(installment)

    suspend fun getInstallments(testId: UUID) : Flow<List<InstallmentsEntity>> = flow {
        emit(stingyLocalDataSource.getInstallments(testId))
    }

    suspend fun deleteInstallments(connectorId: UUID) = stingyLocalDataSource.deleteInstallments(connectorId)

}