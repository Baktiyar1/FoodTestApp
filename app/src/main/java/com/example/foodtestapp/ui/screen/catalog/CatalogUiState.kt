package com.example.foodtestapp.ui.screen.catalog

import androidx.compose.runtime.Stable
import com.example.foodtestapp.models.Category
import com.example.foodtestapp.models.FilterTag
import com.example.foodtestapp.models.Product
import com.example.foodtestapp.models.Tag
import kotlinx.collections.immutable.ImmutableList
import javax.annotation.concurrent.Immutable

@Stable
sealed class CatalogUiState {

    @Immutable
    data object Loading : CatalogUiState()

    @Stable
    data class Loaded(
        val categories: ImmutableList<Category>,
        val nowProducts: List<Product>,
        val countsMap: Map<Int, Int>,
        val tags: ImmutableList<Tag>,
        val totalPrice: Double,
        val filterTags: List<FilterTag>
    ) : CatalogUiState()

    @Immutable
    data class Error(val error: String) : CatalogUiState()

}