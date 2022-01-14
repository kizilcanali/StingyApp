package com.alikizilcan.stingyapp.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.alikizilcan.stingyapp.domain.TransactionUseCase
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(private val transactionUseCase: TransactionUseCase) :
    ViewModel() {

    val navigation = Navigation()

    private val _transactionsList : MutableLiveData<List<Transaction>> = MutableLiveData()
    val transactionsList: LiveData<List<Transaction>> = _transactionsList

    val itemClickListener: (Transaction) -> Unit = {
        deleteTransaction(it)
        fetchTransactions()
    }

    init {
        fetchTransactions()
    }

    private fun fetchTransactions(){
        viewModelScope.launch {
            transactionUseCase.fetchTransactions().collect {
                _transactionsList.value = it
            }
        }
    }

    private fun deleteTransaction(transaction:Transaction){
        viewModelScope.launch {
            transactionUseCase.deleteTransaction(transaction)
        }
    }

    fun addTransaction(navDirections: NavDirections){
        navigation.navigate(navDirections)
    }
}