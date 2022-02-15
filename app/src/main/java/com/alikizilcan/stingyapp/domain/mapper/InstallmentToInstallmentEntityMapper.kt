package com.alikizilcan.stingyapp.domain.mapper

import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.domain.model.Installments
import com.alikizilcan.stingyapp.infra.orZero
import javax.inject.Inject

class InstallmentToInstallmentEntityMapper @Inject constructor() {
    fun mapFromInstallment(installment: Installments) : InstallmentsEntity {
        return InstallmentsEntity(
            name = installment.name,
            installmentCount = installment.installmentCount.orZero(),
            monthlyPayment = installment.monthlyPayment.orZero(),
            date = installment.date,
            isPaid = installment.isPaid.orZero(),
            connectorId = installment.connectorId
        )
    }
}