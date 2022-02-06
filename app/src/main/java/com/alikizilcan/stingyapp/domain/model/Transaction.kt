package com.alikizilcan.stingyapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Transaction(
    val id: UUID,
    val transactionName: String?,
    val transactionAmount: Double?,
    val transactionDate: String?,
    val category: String?,
    val transactionType: String?,
    ) : Parcelable