package com.alikizilcan.stingyapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Installments(
    val name: String,
    val installmentCount: Int,
    val monthlyPayment: Double,
    val date: String,
    val isPaid: Int?,
    val connectorId: UUID
) : Parcelable
