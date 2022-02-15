package com.alikizilcan.stingyapp.infra

import com.alikizilcan.stingyapp.R

data class ViewState(
    val category: String
) {

    fun setIcon(): Int {
        return when (category) {
            Categories.FUEL.name -> R.drawable.ic_food
            Categories.JEWELRY.name -> R.drawable.ic_diamond
            Categories.TECHNOLOGY.name -> R.drawable.ic_grocery
            Categories.TRANSPORTATION.name -> R.drawable.ic_transportation
            else -> R.drawable.ic_health
        }
    }
}
