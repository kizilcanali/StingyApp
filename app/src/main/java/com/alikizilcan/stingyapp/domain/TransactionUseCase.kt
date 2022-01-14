package com.alikizilcan.stingyapp.domain

import com.alikizilcan.stingyapp.data.TransactionRepository
import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import com.alikizilcan.stingyapp.domain.mapper.TransactionEntityToTransactionMapper
import com.alikizilcan.stingyapp.domain.mapper.TransactionToTransactionEntityMapper
import com.alikizilcan.stingyapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val transactionEntityMapper: TransactionEntityToTransactionMapper,
    private val transactionMapper: TransactionToTransactionEntityMapper
){

    fun fetchTransactions(): Flow<List<Transaction>>{
        return transactionRepository.fetchTransactions().map { resource ->
            resource.map {
                transactionEntityMapper.mapFromTransactionEntity(it)
            }
        }
    }

    suspend fun insertTransaction(transaction: Transaction){
        val transactionEntity = transactionMapper.mapFromTransaction(transaction)
        transactionRepository.insertTransactions(transactionEntity)
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        val transactionEntity = transactionMapper.mapFromTransaction(transaction)
        transactionRepository.deleteTransaction(transactionEntity)
    }

    suspend fun updateTransaction(paidInstallment: Int) =
        transactionRepository.updateTransaction(paidInstallment)

}