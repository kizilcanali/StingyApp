package com.alikizilcan.stingyapp.domain.mapper

import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.orZero
import javax.inject.Inject

class TransactionToTransactionEntityMapper @Inject constructor(){
    fun mapFromTransaction(transaction: Transaction) : TransactionsEntity{
        return TransactionsEntity(
            id = transaction.id,
            transactionName = transaction.transactionName.orEmpty(),
            transactionAmount = transaction.transactionAmount.orZero(),
            transactionDate = transaction.transactionDate.orEmpty(),
            transactionCategory = transaction.category.orEmpty(),
            transactionType = transaction.transactionType.orEmpty(),
            installment = transaction.installment.orZero(),
            paidInstallments = transaction.paidInstallment.orZero()
        )
    }
}