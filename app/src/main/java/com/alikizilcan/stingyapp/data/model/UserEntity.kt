package com.alikizilcan.stingyapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_information")
data class UserEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "budget") val budget: Double,
    @ColumnInfo(name = "iban") val iban: Int,
    @ColumnInfo(name = "account_number") val accountNumber: Double,
    )
