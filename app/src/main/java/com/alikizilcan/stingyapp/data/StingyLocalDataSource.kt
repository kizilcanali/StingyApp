package com.alikizilcan.stingyapp.data

import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import java.util.*
import javax.inject.Inject


class StingyLocalDataSource @Inject constructor(private val stingyDao: StingyDao) {

    suspend fun insertTransaction(transactionsEntity: TransactionsEntity) =
        stingyDao.insertTransaction(transactionsEntity)

    suspend fun deleteTransaction(transactionsEntity: TransactionsEntity) =
        stingyDao.deleteTransaction(transactionsEntity)

    suspend fun getBudget() = stingyDao.getBudget()
    suspend fun updateBudget(newBudget: Double) = stingyDao.updateBudget(newBudget)
    suspend fun fetchTransactions() = stingyDao.fetchTransactions()

    suspend fun insertInstallment(installment: InstallmentsEntity) =
        stingyDao.insertInstallment(installment)

    suspend fun getTransactionsWithInstallments(connectionId: UUID) =
        stingyDao.getTransactionWithInstallments(connectionId)


    suspend fun getInstallments(testId: UUID) = stingyDao.getInstallments(testId)

    /*HERE MIGHT CHANGE*/
    //suspend fun updateTransaction(paidInstallment: Int) = stingyDao.updateTransaction(paidInstallment)


}