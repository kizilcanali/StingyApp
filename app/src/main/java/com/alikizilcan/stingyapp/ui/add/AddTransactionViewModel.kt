package com.alikizilcan.stingyapp.ui.add

import android.app.DatePickerDialog
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.alikizilcan.stingyapp.R
import com.alikizilcan.stingyapp.domain.TransactionUseCase
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.Categories
import com.alikizilcan.stingyapp.infra.Categoriesx
import com.alikizilcan.stingyapp.infra.Categoriesx.listOfCategories
import com.alikizilcan.stingyapp.infra.Category
import com.alikizilcan.stingyapp.infra.ViewState
import com.alikizilcan.stingyapp.infra.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    var categoryIcon: MutableLiveData<String> = MutableLiveData()
    var categoryColor: MutableLiveData<String> = MutableLiveData()
    var type: MutableLiveData<String> = MutableLiveData()
    var installmentCount: MutableLiveData<String> = MutableLiveData()

    //-------------------

    var viewState: MutableLiveData<ViewState> = MutableLiveData()

    var _categoryName: MutableLiveData<String> = MutableLiveData()
    var categoryName: LiveData<String> = _categoryName

    fun addTransaction(
        navDirections: NavDirections
    ) {
        viewModelScope.launch {
            transactionUseCase.insertTransaction(
                Transaction(
                    id = 0,
                    transactionName = name.value,
                    transactionAmount = amount.value?.toDouble(),
                    transactionDate = date.value,
                    category = category.value,
                    categoryIcon = 0,
                    categoryColor = "",
                    transactionType = type.value,
                    installment = installmentCount.value?.toInt(),
                    paidInstallment = 0
                )
            )
            navigation.navigate(navDirections)
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
                date.value = "$mDay/${mMonth + 1}/$mYear"
            }, year, month, day
        )
        pickerDialog.show()
    }

    @DrawableRes
    fun updateViewState(): Int{
        return when (categoryName.value?.toUpperCase()) {
            Categories.FUEL.name -> R.drawable.ic_food
            Categories.JEWELRY.name -> R.drawable.ic_diamond
            Categories.TECHNOLOGY.name -> R.drawable.ic_grocery
            Categories.TRANSPORTATION.name -> R.drawable.ic_transportation
            else -> R.drawable.ic_health
        }

    }
}

