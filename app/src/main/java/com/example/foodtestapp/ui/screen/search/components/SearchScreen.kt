package com.example.foodtestapp.ui.screen.search.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.example.foodtestapp.ui.components.ErrorScreen
import com.example.foodtestapp.ui.components.LoadingScreen
import com.example.foodtestapp.ui.screen.search.SearchUiState

@ExperimentalFoundationApi
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onBack: () -> Unit,
    updateText: (String) -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    when (uiState) {
        SearchUiState.Loading -> LoadingScreen()
        is SearchUiState.Error -> ErrorScreen(errorMessage = uiState.error)
        is SearchUiState.Loaded -> SearchLoadedScreen(
            uiState = uiState,
            onBack = onBack,
            updateText = updateText,
            navigateToDetails = navigateToDetails
        )
    }
}