package com.alikizilcan.stingyapp.domain.model

import java.util.*

data class Installments(
    val id: String,
    val name: String,
    val installmentCount: Int,
    val monthlyPayment: Double,
    val date: String,
    val isPaid: Int?,
    val connectorId: UUID
)
