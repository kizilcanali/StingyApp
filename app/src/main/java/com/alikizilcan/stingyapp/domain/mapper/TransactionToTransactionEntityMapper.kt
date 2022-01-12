package com.alikizilcan.stingyapp.domain.mapper

import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.orZero
import javax.inject.Inject

class TransactionToTransactionEntityMapper @Inject constructor(){
    fun mapFromTransaction(transaction: Transaction) : TransactionsEntity{
        return TransactionsEntity(
            id = transaction.id,
            transactionName = transaction.transactionName,
            transactionAmount = transaction.transactionAmount,
            transactionDate = transaction.transactionDate,
            transactionCategory = transaction.category,
            transactionType = transaction.transactionType.orEmpty(),
            installment = transaction.installment,
            paidInstallments = transaction.paidInstallment
        )
    }
}