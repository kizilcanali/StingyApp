package com.alikizilcan.stingyapp.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alikizilcan.stingyapp.domain.TransactionUseCase
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.Categories
import com.alikizilcan.stingyapp.infra.Category
import com.alikizilcan.stingyapp.infra.di.TYPE
import com.alikizilcan.stingyapp.infra.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionUseCase: TransactionUseCase
) : ViewModel() {

    val navigation = Navigation()
    val transactionCategories: List<Category> = Categories.listOfCategories
    val typeList: List<String> = listOf(TYPE.EXPENSE.toString(), TYPE.INCOME.toString())

    fun addTransaction(
        name: String,
        amount: Double,
        date: String,
        category: String,
        type: String,
        installmentCount: Int
    ) {
        viewModelScope.launch {
            transactionUseCase.insertTransaction(
                Transaction(
                    id = 0,
                    transactionName = name,
                    transactionAmount = amount,
                    transactionDate = date,
                    category = category,
                    transactionType = type,
                    installment = installmentCount,
                    paidInstallment = 0
                )
            )
        }
    }



}

