package com.example.foodtestapp.ui.screen.catalog.model

import androidx.compose.runtime.Immutable

@Immutable
data class CatalogPriceModel(
    val productId: Int,
    val priceCurrent: Double,
    val priceOld: Double,
    val isSearch: Boolean
)