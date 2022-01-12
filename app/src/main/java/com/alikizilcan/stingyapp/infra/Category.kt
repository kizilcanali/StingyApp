package com.alikizilcan.stingyapp.infra

class Category(
    val name: String,
    val icon: Int?,
    val color: String?
){
    override fun toString(): String {
        return name
    }
}