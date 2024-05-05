package com.example.foodtestapp.ui.screen.card

sealed class CardUiEvents {
    data class UpdateCacheProduct(val productId: Int, val isMinus: Boolean) : CardUiEvents()
}