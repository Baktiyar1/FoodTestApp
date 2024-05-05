package com.example.foodtestapp.ui.screen.details

sealed class DetailsUiEvents {
    data class UpdateProduct(val isMinus: Boolean) : DetailsUiEvents()
}