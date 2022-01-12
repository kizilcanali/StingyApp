package com.alikizilcan.stingyapp.domain.model

import android.os.Parcelable
import com.alikizilcan.stingyapp.infra.Category
import com.alikizilcan.stingyapp.infra.di.TYPE
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

@Parcelize
data class Transaction(
    val id: Int,
    val transactionName: String,
    val transactionAmount: Double,
    val transactionDate: String,
    val category: String,
    val transactionType: String,
    //val category: @RawValue Category,
    //val transactionType: TYPE,
    val installment: Int?,
    val paidInstallment: Int?
    ) : Parcelable