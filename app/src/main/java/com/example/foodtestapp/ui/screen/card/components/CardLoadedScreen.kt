package com.example.foodtestapp.ui.screen.card.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.domain.utils.DEFAULT_INT
import com.example.domain.utils.convertPriceText
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.BottomButton
import com.example.foodtestapp.ui.screen.card.CardUiState
import com.example.foodtestapp.ui.screen.card.model.CardItemModel
import com.example.foodtestapp.ui.screen.catalog.components.EmptyProducts
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.dp56
import com.example.foodtestapp.ui.theme.dp72

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun CardLoadedScreen(
    uiState: CardUiState.Loaded,
    navigateToBack: () -> Unit,
    updateCacheProduct: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        containerColor = White,
        topBar = { CardToolBar(navigateToBack = navigateToBack) },
        bottomBar = {
            BottomButton(
                text = String.format(
                    stringResource(R.string.txt_order), convertPriceText(uiState.totalPrice)
                )
            )
        },
    ) {
        if (uiState.cardProducts.isNotEmpty()) LazyColumn(
            modifier = modifier.padding(top = dp56, bottom = dp72)
        ) {
            items(items = uiState.cardProducts) {
                CardItem(
                    cardItemModel = CardItemModel(
                        productId = it.id,
                        productName = it.name,
                        priceCurrent = it.priceCurrent,
                        priceOld = it.priceOld,
                        saveCount = uiState.countsMap[it.id] ?: DEFAULT_INT
                    ), updateCacheProduct = updateCacheProduct
                )
            }
        } else EmptyProducts(resId = R.string.txt_products_are_missing)
    }
}