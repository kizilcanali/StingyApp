package com.alikizilcan.stingyapp.domain.mapper

import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.orZero
import com.alikizilcan.stingyapp.infra.toUIModel
import javax.inject.Inject

class TransactionEntityToTransactionMapper @Inject constructor(){
    fun mapFromTransactionEntity(entity: TransactionsEntity) : Transaction {
        return Transaction(
            id = entity.id,
            transactionName = entity.transactionName,
            transactionAmount = entity.transactionAmount.orZero(),
            transactionDate = entity.transactionDate,
            category = entity.transactionCategory,
            transactionType = entity.transactionType,
            installments = entity.installments!!.toUIModel()
        )
    }
}