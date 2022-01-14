package com.alikizilcan.stingyapp.infra

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("setVisibility")
fun setVisibility(view: View, state:Boolean){
    if(state){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}