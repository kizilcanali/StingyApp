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

    suspend fun getInstallments(testId: UUID) = stingyDao.getInstallments(testId)

    suspend fun deleteInstallments(connectorId: UUID) = stingyDao.deleteInstallments(connectorId)

    suspend fun updateIsPaid(isPaid: Int, id: Long) = stingyDao.updateIsPaid(isPaid, id)

    suspend fun updateTransaction(newList: String, id: UUID) = stingyDao.updateTransaction(newList, id)

    suspend fun getTotalTransactionByCategory() = stingyDao.getTotalTransactionByCategory()

    suspend fun getTotalTransactions() = stingyDao.getTotalTransactions()

    suspend fun insertBudgetFirst() = stingyDao.insertBudgetFirst()

}