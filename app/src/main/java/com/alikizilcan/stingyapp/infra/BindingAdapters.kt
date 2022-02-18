package com.alikizilcan.stingyapp.infra

import android.annotation.SuppressLint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alikizilcan.stingyapp.R
import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.ui.transactions.InstallmentsAdapter

@BindingAdapter("setVisibility")
fun setVisibility(view: View, state:Boolean){
    if(state){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter("setChecked")
fun setCheckedValue(view: CheckBox, isPaid: Int){
    view.isSelected = isPaid == 1
}


