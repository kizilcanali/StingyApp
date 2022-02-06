package com.alikizilcan.stingyapp.data

import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import com.alikizilcan.stingyapp.data.model.relations.TransactionsAndInstallments
import com.alikizilcan.stingyapp.domain.mapper.InstallmentEntityToInstallmentMapper
import com.alikizilcan.stingyapp.infra.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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

    suspend fun getTransactionWithInstallments(connectionId: UUID): Flow<List<TransactionsAndInstallments>> = flow {
        emit(stingyLocalDataSource.getTransactionsWithInstallments(connectionId))
    }
}