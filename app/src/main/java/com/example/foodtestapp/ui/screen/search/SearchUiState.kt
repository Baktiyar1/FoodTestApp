package com.example.foodtestapp.ui.screen.search

import androidx.compose.runtime.Stable
import com.example.foodtestapp.models.Product
import javax.annotation.concurrent.Immutable

@Stable
sealed class SearchUiState {

    @Immutable
    data object Loading : SearchUiState()

    @Stable
    data class Loaded(val products: List<Product>, val searchText: String) : SearchUiState()

    @Immutable
    data class Error(val error: String) : SearchUiState()

}