package com.example.foodtestapp.ui.screen.details

import com.example.domain.utils.DISCOUNT_STRING
import com.example.foodtestapp.models.FilterTag
import com.example.foodtestapp.models.Product
import com.example.foodtestapp.ui.product.ProductCountRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.collections.immutable.immutableListOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DetailsViewModelTest {

    @MockK
    private lateinit var productCountRepository: ProductCountRepository

    @MockK
    private lateinit var viewModel: DetailsViewModel

    private lateinit var testProduct: Product

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testProduct = Product(
            id = TEST_PRODUCT_ID,
            name = TEST_PRODUCT_NAME,
            description = TEST_PRODUCT_DESCRIPTION,
            image = TEST_PRODUCT_IMAGE,
            priceCurrent = TEST_PRODUCT_PRICE_CURRENT,
            priceOld = TEST_PRODUCT_PRICE_OLD,
            categoryId = TEST_CATEGORY_ID,
            measure = TEST_PRODUCT_MEASURE,
            measureUnit = TEST_PRODUCT_MEASURE_UNIT,
            energy = TEST_PRODUCT_DATA,
            proteins = TEST_PRODUCT_DATA,
            fats = TEST_PRODUCT_DATA,
            carbohydrates = TEST_PRODUCT_DATA,
            tagIds = immutableListOf(TEST_TAG_ID),
            filterTags = immutableListOf(FilterTag(id = 6, title = DISCOUNT_STRING))
        )
        every { productCountRepository.nowProduct } returns testProduct
        every { productCountRepository.getCountById(testProduct.id) } returns 0
        every { viewModel.uiState.value } returns DetailsUiState.Loaded(testProduct, TEST_COUNT)
    }

    @Test
    fun `init sets ui state to loaded`() = runBlocking {
        val uiState = viewModel.uiState.value

        assert(uiState is DetailsUiState.Loaded)
        val loadedState = uiState as DetailsUiState.Loaded
        assert(loadedState.product.id == TEST_PRODUCT_ID)
        assert(loadedState.product.name == TEST_PRODUCT_NAME)
        assert(loadedState.count == TEST_COUNT)
    }

    @Test
    fun `on ui event update product calls change count`() = runBlocking {
        every {
            productCountRepository.getSaveCount(saveCount = TEST_COUNT, isMinus = TEST_IS_MINUS)
        } returns TEST_SAVE_COUNT
        every { viewModel.onUiEvent(DetailsUiEvents.UpdateProduct(isMinus = TEST_IS_MINUS)) } answers {
            productCountRepository.getSaveCount(saveCount = TEST_COUNT, isMinus = TEST_IS_MINUS)
        }

        viewModel.onUiEvent(DetailsUiEvents.UpdateProduct(isMinus = TEST_IS_MINUS))

        verify(exactly = 1) { productCountRepository.getSaveCount(TEST_COUNT, TEST_IS_MINUS) }

        val uiStateBefore = viewModel.uiState.value as DetailsUiState.Loaded
        assert(uiStateBefore.count == TEST_COUNT)
    }

    companion object {
        private const val TEST_PRODUCT_ID = -1
        private const val TEST_PRODUCT_NAME = "Product 1"
        private const val TEST_COUNT = 0
        private const val TEST_SAVE_COUNT = 1
        private const val TEST_IS_MINUS = false
        private const val TEST_CATEGORY_ID = -111
        private const val TEST_TAG_ID = -11
        private const val TEST_PRODUCT_DESCRIPTION = "Test product description"
        private const val TEST_PRODUCT_IMAGE = "Test product image url"
        private const val TEST_PRODUCT_PRICE_CURRENT = -1.0
        private const val TEST_PRODUCT_PRICE_OLD = -2.0
        private const val TEST_PRODUCT_MEASURE = -1
        private const val TEST_PRODUCT_MEASURE_UNIT = "Test product measure unit"
        private const val TEST_PRODUCT_DATA = -1.0
    }
}