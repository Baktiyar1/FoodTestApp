package com.example.foodtestapp.ui.screen.search

import com.example.domain.utils.EMPTY_STRING
import com.example.foodtestapp.ui.product.ProductCountRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {

    @MockK
    private lateinit var productCountRepository: ProductCountRepository

    @MockK
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = SearchViewModel(productCountRepository = productCountRepository)
    }

    @Test
    fun `update search text should update searchText and emit loaded`() {
        val expectedState =
            SearchUiState.Loaded(products = emptyList(), searchText = TEST_SEARCH_TEXT)

        every { productCountRepository.allProducts } returns mapOf()
        viewModel.onUiEvent(SearchUiEvents.UpdateSearchText(TEST_SEARCH_TEXT))

        val result = viewModel.uiState.value
        assert(result == expectedState)
    }

    @Test
    fun `update now product id should update now product in product count repository and emit loaded`() {
        val expectedState = SearchUiState.Loaded(products = emptyList(), searchText = EMPTY_STRING)

        every { productCountRepository.allProducts } returns mapOf()
        every { productCountRepository.getNowProduct(TEST_PRODUCT_ID) } returns mockk()
        every { productCountRepository.nowProduct = any() } just Runs
        viewModel.onUiEvent(SearchUiEvents.UpdateNowProductId(TEST_PRODUCT_ID))

        verify(exactly = 1) { productCountRepository.nowProduct = any() }
        val result = viewModel.uiState.value
        assert(result == expectedState)
    }

    companion object {
        private const val TEST_SEARCH_TEXT = "test search text"
        private const val TEST_PRODUCT_ID = -1
    }
}