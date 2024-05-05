package com.example.foodtestapp.ui.screen.catalog.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import com.example.foodtestapp.models.Category
import com.example.foodtestapp.models.Product
import com.example.foodtestapp.ui.components.ErrorScreen
import com.example.foodtestapp.ui.components.LoadingScreen
import com.example.foodtestapp.ui.components.OnLifecycleEvent
import com.example.foodtestapp.ui.screen.catalog.CatalogUiState
import com.example.foodtestapp.ui.theme.FoodTestAppTheme
import kotlinx.collections.immutable.immutableListOf

@ExperimentalMaterial3Api
@Composable
fun CatalogScreen(
    catalogUiState: CatalogUiState,
    navigateToDetails: (Int) -> Unit,
    navigateToCard: () -> Unit,
    navigateToSearch: () -> Unit,
    updateProductsForTags: () -> Unit,
    updateCategory: (Int) -> Unit,
    onChangeFilterTag: (Int) -> Unit,
    updateProduct: (Int, Boolean) -> Unit,
    getInfo: () -> Unit,
) {
    FoodTestAppTheme {
        when (catalogUiState) {
            CatalogUiState.Loading -> LoadingScreen()
            is CatalogUiState.Error -> ErrorScreen(errorMessage = catalogUiState.error)
            is CatalogUiState.Loaded -> CatalogLoadedScreen(
                catalogUiState = catalogUiState,
                navigateToDetails = navigateToDetails,
                navigateToSearch = navigateToSearch,
                navigateToCard = navigateToCard,
                updateCategory = updateCategory,
                updateProduct = updateProduct,
                onChangeFilterTag = onChangeFilterTag,
                updateProductsForTags = updateProductsForTags
            )
        }

        OnLifecycleEvent { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> getInfo()
                else -> Unit
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun CatalogScreenPreview() {
    CatalogLoadedScreen(
        catalogUiState = CatalogUiState.Loaded(
            categories = immutableListOf(Category.unknown),
            nowProducts = listOf(Product.unknown, Product.unknown, Product.unknown),
            countsMap = mapOf(),
            tags = immutableListOf(),
            totalPrice = 0.0,
            filterTags = immutableListOf(),
        ),
        navigateToDetails = {},
        navigateToCard = {},
        navigateToSearch = {},
        updateCategory = {},
        updateProduct = { _, _ -> },
        onChangeFilterTag = {},
        updateProductsForTags = {}
    )
}