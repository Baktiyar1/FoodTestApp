package com.example.foodtestapp.models

import androidx.compose.runtime.Immutable

@Immutable
data class Category(val id: Int, val name: String) {
    companion object {
        val unknown = Category(id = -1, name = "Empty")
    }
}