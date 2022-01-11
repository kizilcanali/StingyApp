package com.alikizilcan.stingyapp.data

import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import javax.inject.Inject


class StingyLocalDataSource @Inject constructor(private val stingyDao: StingyDao ) {

    suspend fun insertTransaction(transactionsEntity: TransactionsEntity) = stingyDao.insertTransaction(transactionsEntity)
    suspend fun deleteTransaction(transactionsEntity: TransactionsEntity) = stingyDao.deleteTransaction(transactionsEntity)
    suspend fun updateBudget(newBudget: Double) = stingyDao.updateBudget(newBudget)
    suspend fun fetchTransactions() = stingyDao.fetchTransactions()
    /*HERE MIGHT CHANGE*/
    suspend fun updateTransaction(paidInstallment: Int) = stingyDao.updateTransaction(paidInstallment)


}