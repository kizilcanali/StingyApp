package com.alikizilcan.stingyapp.infra

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

@BindingAdapter("setVisibility")
fun setVisibility(view: View, state:Boolean){
    if(state){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter("setFormatText")
fun setFormatNumber(view: TextView, num: Double){
    val df = DecimalFormat("#,###.00")
    view.text = df.format(num)
}

@BindingAdapter("setBudgetColor")
fun setBudgetColor(view: TextView, amount: Double){
    when {
        amount > 0 -> view.setTextColor(Color.GREEN)
        amount < 0 -> view.setTextColor(Color.RED)
        else -> view.setTextColor(Color.BLACK)
    }
}

