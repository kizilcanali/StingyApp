package com.alikizilcan.stingyapp.data

import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import com.alikizilcan.stingyapp.infra.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val stingyLocalDataSource: StingyLocalDataSource) {


    fun fetchTransactions(): Flow<List<TransactionsEntity>> = flow{
        emit(stingyLocalDataSource.fetchTransactions())
    }

    suspend fun insertTransactions(transactionsEntity: TransactionsEntity) =
        stingyLocalDataSource.insertTransaction(transactionsEntity)

    suspend fun deleteTransaction(transactionsEntity: TransactionsEntity) =
        stingyLocalDataSource.deleteTransaction(transactionsEntity)


    /* HERE MIGHT CHANGE */
    suspend fun updateTransaction(paidInstallment: Int) =
        stingyLocalDataSource.updateTransaction(paidInstallment)
}