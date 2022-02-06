package com.alikizilcan.stingyapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInformation(
    val id: Int,
    val budget: Double,
    val iban: Int,
    val accountNumber: Int
) : Parcelable
