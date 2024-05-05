package com.example.foodtestapp.ui.screen.search

sealed class SearchUiEvents {
    data class UpdateSearchText(val text: String) : SearchUiEvents()
    data class UpdateNowProductId(val productId: Int) : SearchUiEvents()
}