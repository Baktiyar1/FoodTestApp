package com.example.foodtestapp.base

sealed class BaseUiEvents {
    data class UpdateSavedCountAndTotalPrice(val productId: Int, val isMinus: Boolean) :
        BaseUiEvents()
}