package com.example.foodtestapp.ui.screen.details.components

import androidx.compose.runtime.Composable
import com.example.foodtestapp.ui.components.ErrorScreen
import com.example.foodtestapp.ui.components.LoadingScreen
import com.example.foodtestapp.ui.screen.details.DetailsUiState

@Composable
fun DetailsScreen(
    uiState: DetailsUiState,
    onBack: () -> Unit,
    updateProduct: (Boolean) -> Unit,
) {
    when (uiState) {
        DetailsUiState.Loading -> LoadingScreen()
        is DetailsUiState.Error -> ErrorScreen(errorMessage = uiState.error)
        is DetailsUiState.Loaded -> DetailsLoadedScreen(
            uiState = uiState,
            onBack = onBack,
            updateProduct = updateProduct,
        )
    }
}