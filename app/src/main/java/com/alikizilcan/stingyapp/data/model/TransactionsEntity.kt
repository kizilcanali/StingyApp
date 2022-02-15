package com.alikizilcan.stingyapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "transactions")
data class TransactionsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: UUID,
    @ColumnInfo(name = "transaction_name") val transactionName: String?,
    @ColumnInfo(name = "transaction_amount") val transactionAmount: Double?,
    @ColumnInfo(name = "transaction_date") val transactionDate: String?,
    @ColumnInfo(name = "category") val transactionCategory: String?,
    @ColumnInfo(name = "type") val transactionType: String?,
    @ColumnInfo(name = "installments") val installments: String?
    )
