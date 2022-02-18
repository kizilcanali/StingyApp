package com.alikizilcan.stingyapp.infra

import com.alikizilcan.stingyapp.R

object Categoriesx{
    val listOfCategories: List<Category> = listOf(
        Category("Transportation", R.drawable.ic_transportation, ""),
        Category("Fuel", 0, ""),
        Category("Food and Beverage", R.drawable.ic_food, ""),
        Category("Groceries", R.drawable.ic_grocery, ""),
        Category("Technology", 0, ""),
        Category("Clothing", 0, ""),
        Category("Jewelry", R.drawable.ic_diamond, ""),
        Category("Health", R.drawable.ic_health, ""),
        Category("Sport", 0, ""),
        Category("Bill", 0, ""),
        Category("Pet", R.drawable.ic_pet, ""),
        Category("Others", 0, ""),
        Category("Gift", 0, ""),
        Category("House", 0, ""),
        Category("Car", 0, ""),
    )
}

enum class Categories(transactionCategory: String){
    TRANSPORTATION("Transportation"),
    FUEL("Fuel"),
    TECHNOLOGY("Technology"),
    JEWELRY("Jewelry"),
    FOODBEVERAGE("Food and Beverage"),


}
