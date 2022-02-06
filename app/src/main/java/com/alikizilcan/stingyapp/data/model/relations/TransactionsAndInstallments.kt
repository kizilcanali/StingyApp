package com.alikizilcan.stingyapp.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.data.model.TransactionsEntity

data class TransactionsAndInstallments(
    @Embedded val transaction: TransactionsEntity,  //major class
    @Relation(
        parentColumn = "id", //primary key of major
        entityColumn = "connector_id"
    )
    val installments: List<InstallmentsEntity>
)