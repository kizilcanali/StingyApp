package com.alikizilcan.stingyapp.infra

fun Int?.orZero() = this ?: 0
fun Double?.orZero() = this ?: 0.0

fun Boolean.toInt() = if (this) 1 else 0

fun Int.buildMonth() : String = if(this/9 == 0) "0${this + 1}" else "${this + 1}"
fun Int.buildDay() : String = if(this/9 == 0) "0${this}" else "$this"
