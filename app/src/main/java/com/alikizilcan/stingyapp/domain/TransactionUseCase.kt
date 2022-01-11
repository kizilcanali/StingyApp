package com.alikizilcan.stingyapp.domain

import com.alikizilcan.stingyapp.data.TransactionRepository
import javax.inject.Inject

class TransactionUseCase @Inject constructor(

    private val transactionRepository: TransactionRepository

){

}