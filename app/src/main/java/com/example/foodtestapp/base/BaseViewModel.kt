package com.example.foodtestapp.base

import androidx.lifecycle.ViewModel
import com.example.domain.utils.DEFAULT_DOUBLE
import com.example.domain.utils.DEFAULT_INT
import com.example.foodtestapp.ui.product.ProductCountRepository

open class BaseViewModel(
    protected open val productCountRepository: ProductCountRepository
) : ViewModel() {

    fun onEvent(baseUiEvents: BaseUiEvents) {
        when (baseUiEvents) {
            is BaseUiEvents.UpdateSavedCountAndTotalPrice -> updateSavedCountAndTotalPrice(state = baseUiEvents)
        }
    }

    private fun updateSavedCountAndTotalPrice(state: BaseUiEvents.UpdateSavedCountAndTotalPrice) {
        val count = productCountRepository.savedCountMap[state.productId] ?: DEFAULT_INT
        val saveCount = productCountRepository.getSaveCount(count, state.isMinus)
        productCountRepository.changeProductCountMap(state.productId, saveCount)
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        productCountRepository.totalPrice = DEFAULT_DOUBLE
        productCountRepository.savedCountMap.forEach {
            productCountRepository.totalPrice += (it.value * productCountRepository.getNowProduct(it.key).priceCurrent)
        }
    }
}