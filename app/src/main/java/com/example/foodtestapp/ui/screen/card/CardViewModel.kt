package com.example.foodtestapp.ui.screen.card

import com.example.foodtestapp.base.BaseUiEvents
import com.example.foodtestapp.base.BaseViewModel
import com.example.foodtestapp.ui.product.ProductCountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(override val productCountRepository: ProductCountRepository) :
    BaseViewModel(productCountRepository = productCountRepository) {

    private val _uiState = MutableStateFlow<CardUiState>(CardUiState.Loading)
    val uiState: StateFlow<CardUiState> = _uiState.asStateFlow()

    init {
        val saveKeys = productCountRepository.savedCountMap.keys
        val cardProducts = productCountRepository.allProducts.values.filter { it.id in saveKeys }
        val contentState = CardUiState.Loaded(
            cardProducts = cardProducts,
            countsMap = productCountRepository.savedCountMap.toMap(),
            totalPrice = productCountRepository.totalPrice
        )
        _uiState.tryEmit(contentState)
    }

    fun onUiEvent(uiEvents: CardUiEvents) {
        when (uiEvents) {
            is CardUiEvents.UpdateCacheProduct -> updateProduct(uiEvents = uiEvents)
        }
    }

    private fun updateProduct(uiEvents: CardUiEvents.UpdateCacheProduct) {
        onEvent(
            BaseUiEvents.UpdateSavedCountAndTotalPrice(productId = uiEvents.productId, isMinus = uiEvents.isMinus)
        )
        _uiState.update { currentState ->
            val state = _uiState.value as? CardUiState.Loaded ?: return@update currentState
            val countsMap = productCountRepository.savedCountMap.toMap()
            val cardProducts = productCountRepository.allProducts.values.toList()
                .filter { it.id in countsMap.keys }
            state.copy(
                cardProducts = cardProducts,
                countsMap = countsMap,
                totalPrice = productCountRepository.totalPrice
            )
        }
    }
}