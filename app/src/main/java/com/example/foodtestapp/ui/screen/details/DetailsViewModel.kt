package com.example.foodtestapp.ui.screen.details

import com.example.foodtestapp.base.BaseUiEvents
import com.example.foodtestapp.base.BaseViewModel
import com.example.foodtestapp.ui.product.ProductCountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(override val productCountRepository: ProductCountRepository) :
    BaseViewModel(productCountRepository = productCountRepository) {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        val product = productCountRepository.nowProduct
        val contentState = DetailsUiState.Loaded(
            product = product, count = productCountRepository.getCountById(product.id)
        )
        _uiState.tryEmit(contentState)
    }

    fun onUiEvent(detailsUiEvents: DetailsUiEvents) {
        when (detailsUiEvents) {
            is DetailsUiEvents.UpdateProduct -> changeCount(isMinus = detailsUiEvents.isMinus)
        }
    }

    private fun changeCount(isMinus: Boolean) {
        onEvent(
            BaseUiEvents.UpdateSavedCountAndTotalPrice(productCountRepository.nowProduct.id, isMinus = isMinus)
        )
        _uiState.update { currentState ->
            val state = _uiState.value as? DetailsUiState.Loaded ?: return@update currentState
            val count = productCountRepository.getSaveCount(state.count, isMinus)
            state.copy(count = count)
        }
    }
}