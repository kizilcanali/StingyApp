package com.alikizilcan.stingyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alikizilcan.stingyapp.domain.HomeUseCase
import com.alikizilcan.stingyapp.infra.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    val navigation = Navigation()

    private var _budget: MutableLiveData<Double> = MutableLiveData()
    val budget: LiveData<Double> = _budget

    init {
        getBudget()
    }

    private fun getBudget() {
        viewModelScope.launch {
            homeUseCase.getBudget().collect {
                _budget.value = it
            }
        }
    }
}