package com.alikizilcan.stingyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alikizilcan.stingyapp.domain.HomeUseCase
import com.alikizilcan.stingyapp.infra.base.BaseViewModel
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private var _budget: MutableLiveData<Double> = MutableLiveData()
    val budget: LiveData<Double> = _budget

    private var _categoriesData: MutableLiveData<ArrayList<PieEntry>> = MutableLiveData()
    val categoriesData: LiveData<ArrayList<PieEntry>> = _categoriesData

    private var _totalTransactions: MutableLiveData<Map<String, Double>> = MutableLiveData()
    val totalTransactions: LiveData<Map<String, Double>> = _totalTransactions

    init {
        getBudget()
        getTransactionsByCategory()
        getTotalTransactions()
    }

    private fun getBudget() {
        viewModelScope.launch {
            homeUseCase.getBudget().collect {
                if (it == null) {
                    insertBudgetFirst()
                }
                _budget.value = it
            }
        }
    }

    private fun getTransactionsByCategory() {
        viewModelScope.launch {
            homeUseCase.getTotalTransactionByCategory().collect {
                _categoriesData.value = it
            }
        }
    }

    private fun getTotalTransactions() {
        viewModelScope.launch {
            homeUseCase.getTotalTransactions().collect {
                _totalTransactions.value = it
            }
        }
    }

    private fun insertBudgetFirst() {
        viewModelScope.launch {
            homeUseCase.insertBudgetFirst()
        }
    }
}