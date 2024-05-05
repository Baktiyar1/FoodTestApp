package com.example.foodtestapp.ui.screen.card.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodtestapp.models.Product
import com.example.foodtestapp.ui.components.ErrorScreen
import com.example.foodtestapp.ui.components.LoadingScreen
import com.example.foodtestapp.ui.screen.card.CardUiState

@Composable
fun CardScreen(
    uiState: CardUiState,
    navigateToBack: () -> Unit,
    updateCacheProduct: (Int, Boolean) -> Unit,
) {
    when (uiState) {
        CardUiState.Loading -> LoadingScreen()
        is CardUiState.Error -> ErrorScreen(errorMessage = uiState.error)
        is CardUiState.Loaded -> CardLoadedScreen(
            uiState = uiState,
            navigateToBack = navigateToBack,
            updateCacheProduct = updateCacheProduct
        )
    }
}

@Preview
@Composable
fun CardScreenPreview() {
    CardLoadedScreen(uiState = CardUiState.Loaded(
        totalPrice = 500.0, countsMap = mapOf(), cardProducts = listOf(
            Product.unknown,
            Product.unknown,
            Product.unknown,
            Product.unknown,
            Product.unknown,
            Product.unknown,
            Product.unknown,
            Product.unknown,
            Product.unknown,
            Product.unknown,
            Product.unknown
        )
    ), navigateToBack = {}, updateCacheProduct = { _, _ -> })
}