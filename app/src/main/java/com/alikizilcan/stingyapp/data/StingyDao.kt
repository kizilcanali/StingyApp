package com.alikizilcan.stingyapp.data

import androidx.room.*
import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import com.alikizilcan.stingyapp.data.model.UserEntity
import com.alikizilcan.stingyapp.domain.model.Installments
import java.util.*

@Dao
interface StingyDao {

    @Insert
    suspend fun insertTransaction(transactionsEntity: TransactionsEntity)

    @Delete
    suspend fun deleteTransaction(transactionsEntity: TransactionsEntity)



    @Query("SELECT budget FROM user_entity")
    suspend fun getBudget(): Double

    @Query("SELECT * FROM user_entity")
    suspend fun getUserInformation(): UserEntity

    @Query("UPDATE user_entity SET budget = :newBudget")
    suspend fun updateBudget(newBudget: Double)

    @Query("SELECT * FROM transactions")
    suspend fun fetchTransactions(): List<TransactionsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInstallment(installment: InstallmentsEntity)

    @Query("SELECT * FROM installments WHERE connector_id = :testId")
    suspend fun getInstallments(testId: UUID): List<InstallmentsEntity>

    @Query("DELETE FROM installments WHERE connector_id = :connectorId")
    suspend fun deleteInstallments(connectorId: UUID)

    @Query("UPDATE installments SET isPaid = :isPaid WHERE id = :id")
    suspend fun updateIsPaid(isPaid: Int, id: Long)

    @Query("UPDATE transactions SET installments = :newList WHERE id = :id")
    suspend fun updateTransaction(newList: String, id: UUID)

    @MapInfo(keyColumn = "category", valueColumn = "totalAmount")
    @Query("SELECT category, SUM(transaction_amount) as totalAmount FROM transactions WHERE type = 'Expense' GROUP BY category ")
    suspend fun getTotalTransactionByCategory() : Map<String, Double>

}