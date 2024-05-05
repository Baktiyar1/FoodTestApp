package com.example.foodtestapp.ui.screen.catalog

sealed class CatalogUiEvents {
    data object GetUpdateInfo : CatalogUiEvents()
    data object UpdateProductsForTags : CatalogUiEvents()
    data class UpdateCategory(val categoryId: Int) : CatalogUiEvents()
    data class UpdateNowProductId(val productId: Int) : CatalogUiEvents()
    data class UpdateFilterTags(val id: Int) : CatalogUiEvents()
    data class UpdateSavedCountAndTotalPrice(val productId: Int, val isMinus: Boolean) :
        CatalogUiEvents()
}