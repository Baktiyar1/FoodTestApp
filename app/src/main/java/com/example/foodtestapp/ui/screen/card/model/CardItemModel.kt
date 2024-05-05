package com.example.foodtestapp.ui.screen.card.model

import androidx.compose.runtime.Immutable
import com.example.domain.utils.DEFAULT_INT

@Immutable
data class CardItemModel(
    val productName: String,
    val productId: Int,
    val priceCurrent: Double,
    val priceOld: Double,
    val saveCount: Int = DEFAULT_INT,
)