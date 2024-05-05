package com.example.foodtestapp.base

import com.example.domain.utils.DISCOUNT_STRING
import com.example.foodtestapp.models.FilterTag
import com.example.foodtestapp.models.Product
import com.example.foodtestapp.ui.product.ProductCountRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.verify
import kotlinx.collections.immutable.immutableListOf
import org.junit.Before
import org.junit.Test

class BaseViewModelTest {

    @MockK
    private lateinit var productCountRepository: ProductCountRepository

    @MockK
    private lateinit var viewModel: BaseViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = BaseViewModel(productCountRepository = productCountRepository)
    }

    @Test
    fun `updateProducts should call changeProductCountMap and updateTotalPrice`() {
        val testProduct = Product(
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

        every { productCountRepository.savedCountMap[TEST_PRODUCT_ID] } returns TEST_COUNT_ACTUAL
        every {
            productCountRepository.getSaveCount(TEST_SAVE_COUNT_ACTUAL, TEST_IS_MINUS)
        } returns TEST_SAVE_COUNT_EXPECTED
        every {
            productCountRepository.changeProductCountMap(TEST_PRODUCT_ID, TEST_SAVE_COUNT_EXPECTED)
        } just Runs
        every { productCountRepository.totalPrice } returns TEST_TOTAL_PRICE
        every { productCountRepository.totalPrice = any() } just Runs
        every { productCountRepository.getNowProduct(TEST_PRODUCT_ID) } returns testProduct
        every { productCountRepository.savedCountMap } returns mutableMapOf(TEST_PRODUCT_ID to TEST_SAVE_COUNT_ACTUAL)

        viewModel.onEvent(BaseUiEvents.UpdateSavedCountAndTotalPrice(TEST_PRODUCT_ID, TEST_IS_MINUS))

        verify(exactly = 1) {
            productCountRepository.getSaveCount(TEST_SAVE_COUNT_ACTUAL, TEST_IS_MINUS)
        }

        verify(exactly = 1) {
            productCountRepository.changeProductCountMap(TEST_PRODUCT_ID, TEST_SAVE_COUNT_EXPECTED)
        }

        verify(exactly = 1) {
            productCountRepository.getNowProduct(TEST_PRODUCT_ID)
        }
    }

    companion object {
        private const val TEST_PRODUCT_ID = -1
        private const val TEST_COUNT_ACTUAL = 1
        private const val TEST_SAVE_COUNT_ACTUAL = 2
        private const val TEST_SAVE_COUNT_EXPECTED = 3
        private const val TEST_TOTAL_PRICE = 0.0
        private const val TEST_IS_MINUS = false
        private const val TEST_CATEGORY_ID = -111
        private const val TEST_TAG_ID = -11
        private const val TEST_PRODUCT_NAME = "Test product name"
        private const val TEST_PRODUCT_DESCRIPTION = "Test product description"
        private const val TEST_PRODUCT_IMAGE = "Test product image url"
        private const val TEST_PRODUCT_PRICE_CURRENT = -1.0
        private const val TEST_PRODUCT_PRICE_OLD = -2.0
        private const val TEST_PRODUCT_MEASURE = -1
        private const val TEST_PRODUCT_MEASURE_UNIT = "Test product measure unit"
        private const val TEST_PRODUCT_DATA = -1.0
    }
}