package com.alikizilcan.stingyapp.infra.di

enum class TYPE(val typeStr: String){
    INCOME("Income"),
    EXPENSE("Expense");


    override fun toString() : String {
        return typeStr
    }
}

