package com.example.foodtestapp.ui.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.domain.utils.EMPTY_STRING
import com.example.foodtestapp.base.BaseViewModel
import com.example.foodtestapp.ui.product.ProductCountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(override val productCountRepository: ProductCountRepository) :
    BaseViewModel(productCountRepository = productCountRepository) {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var searchText by mutableStateOf(EMPTY_STRING)

    init {
        getSearchProduct()
    }

    fun onUiEvent(uiEvent: SearchUiEvents) {
        when (uiEvent) {
            is SearchUiEvents.UpdateSearchText -> updateSearchText(newSearchText = uiEvent.text)
            is SearchUiEvents.UpdateNowProductId -> productCountRepository.nowProduct =
                productCountRepository.getNowProduct(uiEvent.productId)
        }
    }

    private fun updateSearchText(newSearchText: String) {
        searchText = newSearchText
        getSearchProduct()
    }

    private fun getSearchProduct() {
        val products = if (searchText.isEmpty()) emptyList()
        else productCountRepository.allProducts.map { it.value }
            .filter { it.name.lowercase().contains(searchText.lowercase()) }
        val contentUiState = SearchUiState.Loaded(products = products, searchText = searchText)
        _uiState.tryEmit(contentUiState)
    }
}