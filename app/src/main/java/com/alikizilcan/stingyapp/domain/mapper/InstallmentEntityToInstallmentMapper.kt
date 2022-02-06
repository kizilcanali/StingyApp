package com.alikizilcan.stingyapp.domain.mapper

import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.domain.model.Installments
import com.alikizilcan.stingyapp.infra.orZero
import javax.inject.Inject

class InstallmentEntityToInstallmentMapper @Inject constructor() {
    fun mapFromInstallmentEntity(entity: InstallmentsEntity): Installments {
        return Installments(
            id = entity.id,
            name = entity.name.orEmpty(),
            installmentCount = entity.installmentCount.orZero(),
            monthlyPayment = entity.monthlyPayment.orZero(),
            date = entity.date.orEmpty(),
            isPaid = entity.isPaid.orZero(),
            connectorId = entity.connectorId
        )
    }
}