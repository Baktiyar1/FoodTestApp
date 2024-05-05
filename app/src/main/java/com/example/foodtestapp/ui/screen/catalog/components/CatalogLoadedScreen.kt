package com.example.foodtestapp.ui.screen.catalog.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.domain.utils.DEFAULT_DOUBLE
import com.example.domain.utils.convertPriceText
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.BottomButton
import com.example.foodtestapp.ui.screen.catalog.CatalogUiState
import com.example.foodtestapp.ui.screen.catalog.model.CatalogContentScreenModel
import com.example.foodtestapp.ui.screen.catalog.model.FilterBottomSheetModel

@ExperimentalMaterial3Api
@OptIn(ExperimentalFoundationApi::class)
@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState", "SuspiciousIndentation"
)
@Composable
fun CatalogLoadedScreen(
    catalogUiState: CatalogUiState.Loaded,
    navigateToDetails: (Int) -> Unit,
    navigateToCard: () -> Unit,
    navigateToSearch: () -> Unit,
    updateProductsForTags: () -> Unit,
    updateCategory: (Int) -> Unit,
    onChangeFilterTag: (Int) -> Unit,
    updateProduct: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isBottomSheetVisibility by remember { mutableStateOf(value = false) }

    Scaffold(
        topBar = {
            CatalogTopBar(
                navigateToSearch = navigateToSearch,
                setShowBottomSheet = { isBottomSheetVisibility = true },
            )
        },
        bottomBar = {
            if (catalogUiState.totalPrice > DEFAULT_DOUBLE) BottomButton(
                text = String.format(
                    stringResource(R.string.txt_order), convertPriceText(catalogUiState.totalPrice)
                ), onClick = navigateToCard
            )
        },
    ) {
        CatalogContentScreen(
            model = CatalogContentScreenModel(
                totalPrice = catalogUiState.totalPrice,
                categories = catalogUiState.categories,
                nowProducts = catalogUiState.nowProducts,
                countsMap = catalogUiState.countsMap
            ),
            updateCategory = updateCategory,
            navigateToDetails = navigateToDetails,
            updateProduct = updateProduct
        )
    }

    AnimatedVisibility(visible = isBottomSheetVisibility, modifier = modifier.fillMaxSize()) {
        FilterBottomSheet(
            model = FilterBottomSheetModel(filterTags = catalogUiState.filterTags),
            setShowBottomSheet = {
                updateProductsForTags()
                isBottomSheetVisibility = false
            },
            onCheckedChange = onChangeFilterTag
        )
    }
}