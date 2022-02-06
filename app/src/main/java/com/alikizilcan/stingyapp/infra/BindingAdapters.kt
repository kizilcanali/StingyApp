package com.alikizilcan.stingyapp.infra

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.alikizilcan.stingyapp.R

@BindingAdapter("setVisibility")
fun setVisibility(view: View, state:Boolean){
    if(state){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@SuppressLint("ResourceAsColor")
@BindingAdapter("setAmountDecoration")
fun setAmountDecoration(text: TextView, type: String, textString: String){
    if(type == "INCOME"){
        text.text = "+$textString"
        text.setTextColor(R.color.green)
    }else {
        text.text = "-$textString"
        text.setTextColor(R.color.red)
    }
}

