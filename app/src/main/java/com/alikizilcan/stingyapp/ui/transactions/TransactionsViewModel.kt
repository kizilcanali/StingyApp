package com.alikizilcan.stingyapp.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.alikizilcan.stingyapp.domain.TransactionUseCase
import com.alikizilcan.stingyapp.domain.model.Installments
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.base.BaseViewModel
import com.alikizilcan.stingyapp.infra.toDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(private val transactionUseCase: TransactionUseCase) :
    BaseViewModel() {

    private val _transactionsList: MutableLiveData<MutableList<Transaction>> = MutableLiveData()
    val transactionsList: LiveData<MutableList<Transaction>> = _transactionsList

    private var _installmentsList: MutableLiveData<List<Installments>> = MutableLiveData()
    val installmentsList: LiveData<List<Installments>> = _installmentsList

    private var _budget: Double = 0.0
    val budget get() = _budget

    val itemDeleteClickListener: (Transaction) -> Unit = {
        deleteTransaction(it)
        if (it.installments != null) {
            deleteInstallments(it.id)
        }
    }

    val itemCheckBoxListener: (Int, Double, Installments) -> Unit =
        { isPaid, monthlyPayment, installments ->
            viewModelScope.launch {
                if (isPaid == 1) {
                    updateIsPaid(1, installments.id)
                } else {
                    updateIsPaid(0, installments.id)
                }
                setBudget(monthlyPayment, isPaid)
                fetchInstallments(installments.connectorId)
                updateTransaction(installmentsList.value!!.toDataModel(), installments.connectorId)
            }
        }

    init {
        fetchTransactions()
        fetchBudget()
    }

    private fun setBudget(amount: Double, state: Int) {
        viewModelScope.launch {
            if(state == 1){
                _budget -= amount
            } else {
                _budget += amount
            }
            transactionUseCase.updateBudget(newBudget = budget)
        }
    }

    private suspend fun updateIsPaid(isPaidParameter: Int, id: Long) {
            transactionUseCase.updateIsPaid(isPaidParameter, id)
    }

    private fun fetchTransactions() {
        viewModelScope.launch {
            transactionUseCase.fetchTransactions().collect {
                _transactionsList.value = it.toMutableList()
            }
        }
    }

    private fun fetchBudget() {
        runBlocking { transactionUseCase.getBudget().collect { _budget = it } }
    }

    private fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            _transactionsList.value?.remove(transaction)
            transactionUseCase.deleteTransaction(transaction)
        }
    }

    private fun deleteInstallments(connectorId: UUID) {
        viewModelScope.launch {
            transactionUseCase.deleteInstallments(connectorId)
        }
    }

    fun addTransaction(navDirections: NavDirections) {
        baseNavigation.navigate(navDirections)
    }

    private suspend fun fetchInstallments(connectionId: UUID) {
        transactionUseCase.getInstallments(connectionId).collect {
            _installmentsList.value = it
        }
    }

    private suspend fun updateTransaction(newList: String, id: UUID) {
        transactionUseCase.updateTransaction(newList, id)
    }
}