package com.alikizilcan.stingyapp.ui.add

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.alikizilcan.stingyapp.domain.TransactionUseCase
import com.alikizilcan.stingyapp.domain.model.Installments
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.*
import com.alikizilcan.stingyapp.infra.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionUseCase: TransactionUseCase
) : BaseViewModel() {

    val transactionCategories: List<Category> = Categoriesx.listOfCategories
    val isVisibleView: MutableLiveData<Boolean> = MutableLiveData(false)
    var name: MutableLiveData<String> = MutableLiveData()
    var amount: MutableLiveData<String> = MutableLiveData()
    var date: MutableLiveData<String> = MutableLiveData()
    var category: MutableLiveData<String> = MutableLiveData()
    var type: MutableLiveData<String> = MutableLiveData()
    var installmentCount: MutableLiveData<String> = MutableLiveData()

    private var _installmentsList: MutableLiveData<List<Installments>> = MutableLiveData()
    val installmentsList: LiveData<List<Installments>> = _installmentsList

    private var _budget: Double = 0.0
    val budget get() = _budget

    var isPaid: MutableLiveData<Boolean> = MutableLiveData(false)

    fun addTransaction(
        navDirections: NavDirections
    ) {
        runBlocking { transactionUseCase.getBudget().collect { _budget = it } }
        addTransactionOperations(navDirections)
    }

    private fun addTransactionOperations(navDirections: NavDirections) {
        viewModelScope.launch {
            val idValue = UUID.randomUUID()
            if (isVisibleView.value == false) {
                setNewBudget(amount.value!!.toDouble())
                transactionUseCase.updateBudget(newBudget = budget)
                _installmentsList.value = emptyList()
            } else {
                val monthlyPayment =
                    (amount.value!!.toDouble() / installmentCount.value!!.toInt())
                for (i in 1..installmentCount.value!!.toInt()) {
                    transactionUseCase.insertInstallment(
                        Installments(
                            name = name.value!!,
                            installmentCount = installmentCount.value!!.toInt(),
                            monthlyPayment = monthlyPayment,
                            date = setupInstallmentMonths(i - 1, date.value!!),
                            isPaid = isPaid.value!!.toInt(),
                            connectorId = idValue,
                            id = 0
                        )
                    )
                }
                fetchInstallments(idValue)
            }
            transactionUseCase.insertTransaction(
                Transaction(
                    id = idValue,
                    transactionName = name.value,
                    transactionAmount = amount.value?.toDouble(),
                    transactionDate = date.value,
                    category = category.value,
                    transactionType = type.value,
                    installments = installmentsList.value
                )
            )
            baseNavigation.navigate(navDirections)
        }
    }

    private fun setupInstallmentMonths(index: Int, date: String): String {
        val userDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        return userDate.plusMonths(index.toLong()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }

    private fun setNewBudget(amount: Double) {
        when (type.value) {
            "Expense" -> {
                _budget -= amount
            }
            "Income" -> {
                _budget += amount
            }
            else -> {
                //show snackbar!!
            }
        }
    }

    fun setupDate(context: Context) {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)
        val pickerDialog = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
                date.value = "${mDay.buildDay()}/${mMonth.buildMonth()}/$mYear"
            }, year, month, day
        )
        pickerDialog.show()
    }

    private suspend fun fetchInstallments(connectionId: UUID) {
        transactionUseCase.getInstallments(connectionId).collect {
            _installmentsList.value = it
        }
    }
}

