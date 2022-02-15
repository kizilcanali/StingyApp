package com.alikizilcan.stingyapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "installments")
data class InstallmentsEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "installment_count") val installmentCount: Int,
    @ColumnInfo(name = "monthly_payment") val monthlyPayment: Double,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "isPaid") val isPaid: Int?,
    @ColumnInfo(name = "connector_id") val connectorId: UUID
    )
