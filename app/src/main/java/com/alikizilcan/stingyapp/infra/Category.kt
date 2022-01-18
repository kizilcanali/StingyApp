package com.alikizilcan.stingyapp.infra

data class Category(
    var name: String,
    val icon: Int?,
    val color: String?
){
    override fun toString(): String {
        return name
    }
}