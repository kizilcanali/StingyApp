package com.alikizilcan.stingyapp.domain

import com.alikizilcan.stingyapp.data.TransactionRepository
import com.alikizilcan.stingyapp.domain.mapper.InstallmentEntityToInstallmentMapper
import com.alikizilcan.stingyapp.domain.mapper.InstallmentToInstallmentEntityMapper
import com.alikizilcan.stingyapp.domain.mapper.TransactionEntityToTransactionMapper
import com.alikizilcan.stingyapp.domain.mapper.TransactionToTransactionEntityMapper
import com.alikizilcan.stingyapp.domain.model.Installments
import com.alikizilcan.stingyapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class TransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val transactionEntityMapper: TransactionEntityToTransactionMapper,
    private val transactionMapper: TransactionToTransactionEntityMapper,
    private val installmentsMapper: InstallmentToInstallmentEntityMapper,
    private val installmentsEntityMapper: InstallmentEntityToInstallmentMapper
) {

    fun fetchTransactions(): Flow<List<Transaction>> {
        return transactionRepository.fetchTransactions().map { resource ->
            resource.map {
                transactionEntityMapper.mapFromTransactionEntity(it)
            }
        }
    }

    suspend fun insertTransaction(transaction: Transaction) {
        val transactionEntity = transactionMapper.mapFromTransaction(transaction)
        transactionRepository.insertTransactions(transactionEntity)
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        val transactionEntity = transactionMapper.mapFromTransaction(transaction)
        transactionRepository.deleteTransaction(transactionEntity)
    }

    suspend fun getBudget(): Flow<Double> {
        return transactionRepository.getBudget()
    }

    suspend fun updateBudget(newBudget: Double) = transactionRepository.updateBudget(newBudget)

    suspend fun insertInstallment(installment: Installments) {
        val installmentsEntity = installmentsMapper.mapFromInstallment(installment)
        transactionRepository.insertInstallment(installmentsEntity)
    }

    suspend fun getInstallments(testId: UUID): Flow<List<Installments>> {
        return transactionRepository.getInstallments(testId).map { resource ->
            resource.map {
                installmentsEntityMapper.mapFromInstallmentEntity(it)
            }
        }
    }

}