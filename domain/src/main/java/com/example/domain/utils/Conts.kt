package com.example.domain.utils

const val APPLICATION_EXCEPTION = "APPLICATION_EXCEPTION"
const val EMPTY_STRING = ""
const val WITHOUT_MEAT_STRING = "Без мяса"
const val ACUTE_STRING = "Острое"
const val DISCOUNT_STRING = "Со скидкой"
const val DEFAULT_DOUBLE = 0.0
const val DEFAULT_OLD_PRICE = -1.0
const val DEFAULT_INT = 0
const val DEFAULT_FLOAT = 0f

fun convertPriceText(price: Double): String {
    val isWhole = price % 1.0 == 0.0
    return "${if (isWhole) price.toInt() else price}"
}