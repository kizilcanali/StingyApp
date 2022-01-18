package com.alikizilcan.stingyapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alikizilcan.stingyapp.infra.Category
import com.alikizilcan.stingyapp.infra.di.TYPE

@Entity(tableName = "transactions")
data class TransactionsEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "transaction_name") val transactionName: String?,
    @ColumnInfo(name = "transaction_amount") val transactionAmount: Double?,
    @ColumnInfo(name = "transaction_date") val transactionDate: String?,
    @ColumnInfo(name = "category") val transactionCategory: String?,
    @ColumnInfo(name = "category_icon") val categoryIcon: Int?,
    @ColumnInfo(name = "category_color") val categoryColor: String?,
    @ColumnInfo(name = "type") val transactionType: String?,
    @ColumnInfo(name = "installment") val installment: Int?,
    @ColumnInfo(name = "paid_installments") val paidInstallments: Int?,
)
