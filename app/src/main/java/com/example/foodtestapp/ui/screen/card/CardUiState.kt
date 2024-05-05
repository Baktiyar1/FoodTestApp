package com.example.foodtestapp.ui.screen.card

import androidx.compose.runtime.Stable
import com.example.foodtestapp.models.Product
import javax.annotation.concurrent.Immutable

@Stable
sealed class CardUiState {

    @Immutable
    data object Loading : CardUiState()

    @Stable
    data class Loaded(
        val cardProducts: List<Product>, val countsMap: Map<Int, Int>, val totalPrice: Double
    ) : CardUiState()

    @Immutable
    data class Error(val error: String) : CardUiState()

}