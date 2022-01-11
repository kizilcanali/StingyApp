package com.alikizilcan.stingyapp.data

import androidx.room.*
import com.alikizilcan.stingyapp.data.model.TransactionsEntity

@Dao
interface StingyDao {

    @Insert
    suspend fun insertTransaction(transactionsEntity: TransactionsEntity)

    @Delete
    suspend fun deleteTransaction(transactionsEntity: TransactionsEntity)

    @Query("UPDATE user_entity SET budget = :newBudget")
    suspend fun updateBudget(newBudget: Double)

    @Query("SELECT * FROM transactions")
    suspend fun fetchTransactions(): List<TransactionsEntity>

    @Query("UPDATE transactions SET paid_installments = :paidInstallments")
    suspend fun updateTransaction(paidInstallments: Int)


}