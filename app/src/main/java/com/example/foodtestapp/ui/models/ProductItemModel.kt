package com.example.foodtestapp.ui.models

import androidx.compose.runtime.Immutable
import com.example.domain.utils.DEFAULT_INT
import com.example.foodtestapp.models.Product

@Immutable
data class ProductItemModel(
    val product: Product,
    val saveCount: Int = DEFAULT_INT,
    val isSearch: Boolean = false,
)