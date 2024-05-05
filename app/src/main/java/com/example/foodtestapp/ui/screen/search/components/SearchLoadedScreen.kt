package com.example.foodtestapp.ui.screen.search.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.ProductsItem
import com.example.foodtestapp.ui.models.ProductItemModel
import com.example.foodtestapp.ui.screen.catalog.components.EmptyProducts
import com.example.foodtestapp.ui.screen.search.SearchUiState
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.dp12
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp40
import com.example.foodtestapp.ui.theme.dp64

@ExperimentalFoundationApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun SearchLoadedScreen(
    uiState: SearchUiState.Loaded,
    onBack: () -> Unit,
    updateText: (String) -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    Scaffold(modifier = Modifier.background(color = White), topBar = {
        SearchTopBar(searchText = uiState.searchText, onBack = onBack, updateText = updateText)
    }) {
        if (uiState.products.isNotEmpty()) LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(dp16),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(dp12),
            contentPadding = PaddingValues(bottom = dp40),
            userScrollEnabled = true,
            modifier = Modifier
                .background(color = White)
                .padding(start = dp16, end = dp16, bottom = dp16, top = dp64)
        ) {
            items(items = uiState.products) { product ->
                ProductsItem(
                    modifier = Modifier.animateItemPlacement(),
                    productItemModel = ProductItemModel(product = product, isSearch = true),
                    navigateToDetails = navigateToDetails,
                )
            }
        } else EmptyProducts(resId = R.string.txt_search_text_empty)
    }
}