package com.alikizilcan.stingyapp.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.alikizilcan.stingyapp.domain.TransactionUseCase
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.base.BaseViewModel
import com.alikizilcan.stingyapp.infra.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(private val transactionUseCase: TransactionUseCase) :
    BaseViewModel() {

    private val _transactionsList: MutableLiveData<MutableList<Transaction>> = MutableLiveData()
    val transactionsList: LiveData<MutableList<Transaction>> = _transactionsList

    val itemDeleteClickListener: (Transaction) -> Unit = {
        deleteTransaction(it)
    }

    init {
        fetchTransactions()
    }

    private fun fetchTransactions() {
        viewModelScope.launch {
            transactionUseCase.fetchTransactions().collect {
                _transactionsList.value = it.toMutableList()
                println("fetch transactions: $it")
            }
        }
    }

    private fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            _transactionsList.value?.remove(transaction)
            transactionUseCase.deleteTransaction(transaction)
        }
    }

    fun addTransaction(navDirections: NavDirections) {
        baseNavigation.navigate(navDirections)
    }

}