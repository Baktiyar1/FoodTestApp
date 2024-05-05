package com.example.foodtestapp.ui.screen.catalog.model

import androidx.compose.runtime.Stable
import com.example.foodtestapp.models.Category
import com.example.foodtestapp.models.Product
import kotlinx.collections.immutable.ImmutableList

@Stable
data class CatalogContentScreenModel(
    val totalPrice: Double,
    val categories: ImmutableList<Category>,
    val nowProducts: List<Product>,
    val countsMap: Map<Int, Int>
)