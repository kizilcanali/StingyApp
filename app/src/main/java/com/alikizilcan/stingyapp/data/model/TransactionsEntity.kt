package com.alikizilcan.stingyapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "transaction_name") val transactionName: String,
    @ColumnInfo(name = "transaction_amount") val transactionAmount: Double,
    @ColumnInfo(name = "transaction_date") val transactionDate: String,
    @ColumnInfo(name = "category") val transactionCategory: Category,
    @ColumnInfo(name = "type") val transactionType: String,
    @ColumnInfo(name = "installment") val installment: Int?,
    @ColumnInfo(name = "paid_installments") val paidInstallments: Int?,
)
