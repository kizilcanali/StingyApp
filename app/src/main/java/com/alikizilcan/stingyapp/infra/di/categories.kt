package com.alikizilcan.stingyapp.infra.di

import android.graphics.Color
import com.alikizilcan.stingyapp.infra.Category

/*
enum class CATEGORIES(category: Category){

    TRANSPORTATION(Category("Transportation", 0, ""))

}*/
object categories {
    val listOfCategories = listOf(
        Category("Transportation", 0, ""),
        Category("Fuel", 0, ""),
        Category("Food and Bevarage", 0, ""),
        Category("Groceries", 0, ""),
        Category("Technology", 0, ""),
        Category("Clothing", 0, ""),
        Category("Jewelry", 0, ""),
        Category("Health", 0, ""),
        Category("Sport", 0, ""),
        Category("Bill", 0, ""),
        Category("Pet", 0, ""),
        Category("Others", 0, ""),
        Category("Gift", 0, ""),
        Category("House", 0, ""),
        Category("Car", 0, ""),

        Category("Penalty", 0, ""),
        Category("Penalty", 0, ""),
        Category("Penalty", 0, ""),
        Category("Penalty", 0, ""),
        Category("Penalty", 0, ""),
        Category("Penalty", 0, ""),
        Category("Penalty", 0, ""),
        Category("Penalty", 0, ""),
    )
}
