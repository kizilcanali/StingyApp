package com.alikizilcan.stingyapp.ui.add

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.alikizilcan.stingyapp.domain.TransactionUseCase
import com.alikizilcan.stingyapp.domain.model.Installments
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.*
import com.alikizilcan.stingyapp.infra.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionUseCase: TransactionUseCase
) : ViewModel() {

    val navigation = Navigation()
    val transactionCategories: List<Category> = Categoriesx.listOfCategories
    val isVisibleView: MutableLiveData<Boolean> = MutableLiveData(false)
    var name: MutableLiveData<String> = MutableLiveData()
    var amount: MutableLiveData<String> = MutableLiveData()
    var date: MutableLiveData<String> = MutableLiveData()
    var category: MutableLiveData<String> = MutableLiveData()
    var type: MutableLiveData<String> = MutableLiveData()
    var installmentCount: MutableLiveData<String> = MutableLiveData()

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
            transactionUseCase.insertTransaction(
                Transaction(
                    id = idValue,
                    transactionName = name.value,
                    transactionAmount = amount.value?.toDouble(),
                    transactionDate = date.value,
                    category = category.value,
                    transactionType = type.value,
                )
            )

            if (isVisibleView.value == false) {
                setNewAmount(amount.value!!.toDouble())
                transactionUseCase.updateBudget(newBudget = budget)
            } else {
                val monthlyPayment = (amount.value!!.toDouble() / installmentCount.value!!.toInt())
                for (i in 1..installmentCount.value!!.toInt()) {
                    transactionUseCase.insertInstallment(
                        Installments(
                            id = i.toString(),
                            name = name.value!!,
                            installmentCount = installmentCount.value!!.toInt(),
                            monthlyPayment = monthlyPayment,
                            date = setupInstallmentMonths(i - 1, date.value!!),
                            isPaid = isPaid.value!!.toInt(),
                            connectorId = idValue
                        )
                    )
                }
            }
            navigation.navigate(navDirections)
        }
    }

    private fun setupInstallmentMonths(index: Int, date: String): String {
        val userDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        return userDate.plusMonths(index.toLong()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }

    private fun setNewAmount(amount: Double) {
        if (type.value == "Expense") {
            _budget -= amount
        } else if (type.value == "Income") {
            _budget += amount
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


/*

    @DrawableRes
    fun updateViewState(): Int {
        return when (categoryName.value?.uppercase()) {
            Categories.FUEL.name -> R.drawable.ic_food
            Categories.JEWELRY.name -> R.drawable.ic_diamond
            Categories.TECHNOLOGY.name -> R.drawable.ic_grocery
            Categories.TRANSPORTATION.name -> R.drawable.ic_transportation
            else -> R.drawable.ic_health
        }
    }
*/

}

