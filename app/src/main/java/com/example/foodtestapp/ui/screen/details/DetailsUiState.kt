package com.example.foodtestapp.ui.screen.details

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.example.foodtestapp.models.Product

@Stable
sealed class DetailsUiState {
    @Immutable
    data object Loading : DetailsUiState()

    @Stable
    data class Loaded(val product: Product, val count: Int) : DetailsUiState()

    @Immutable
    data class Error(val error: String) : DetailsUiState()
}