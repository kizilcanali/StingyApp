package com.alikizilcan.stingyapp.infra

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

@BindingAdapter("setVisibility")
fun setVisibility(view: View, state:Boolean){
    view.visibility = if(state) View.VISIBLE else View.GONE
}

@BindingAdapter("setFormatText")
fun setFormatNumber(view: TextView, num: Double?){
    val df = DecimalFormat("#,###.00")
    if (num != null)  view.text = df.format(num) else view.text = "0.0"
}

@BindingAdapter("setClickable")
fun setClickable(view: View, state: String?){
    view.isClickable = when(state){
        "Income" -> false
        "Expense" -> true
        else -> false
    }
}


