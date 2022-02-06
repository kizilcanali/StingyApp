package com.alikizilcan.stingyapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alikizilcan.stingyapp.infra.Category
import com.alikizilcan.stingyapp.infra.di.TYPE
import java.util.*

@Entity(tableName = "transactions")
data class TransactionsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: UUID,
    @ColumnInfo(name = "transaction_name") val transactionName: String?,
    @ColumnInfo(name = "transaction_amount") val transactionAmount: Double?,
    @ColumnInfo(name = "transaction_date") val transactionDate: String?,
    @ColumnInfo(name = "category") val transactionCategory: String?,
    @ColumnInfo(name = "type") val transactionType: String?,

    )
