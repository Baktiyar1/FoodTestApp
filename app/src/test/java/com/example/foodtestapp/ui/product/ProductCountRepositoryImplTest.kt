package com.example.foodtestapp.ui.product

import com.example.domain.utils.DISCOUNT_STRING
import com.example.foodtestapp.models.FilterTag
import com.example.foodtestapp.models.Product
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.collections.immutable.immutableListOf
import org.junit.Before
import org.junit.Test

class ProductCountRepositoryImplTest {

    @MockK
    private lateinit var productCountRepository: ProductCountRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should count by id`() {
        every { productCountRepository.getCountById(TEST_PRODUCT_ID) } returns testCountActual
        val actual = productCountRepository.getCountById(TEST_PRODUCT_ID)
        val expend = testCountExpected

        assert(expend == actual)
    }

    @Test
    fun `should now product`() {
        every { productCountRepository.getNowProduct(TEST_PRODUCT_ID) } returns testProductActual
        val actual = productCountRepository.getNowProduct(TEST_PRODUCT_ID)
        val expend = testProductExpected

        assert(expend == actual)
    }

    @Test
    fun `should save count`() {
        every {
            productCountRepository.getSaveCount(TEST_SAVE_COUNT, TEST_IS_MINUS)
        } returns testCountActual
        val actual = productCountRepository.getSaveCount(TEST_SAVE_COUNT, TEST_IS_MINUS)
        val expend = testCountExpected

        assert(expend == actual)
    }

    companion object {
        private const val TEST_CATEGORY_ID = -111
        private const val TEST_TAG_ID = -11
        private const val TEST_PRODUCT_ID = -1
        private const val TEST_PRODUCT_NAME = "Test product name"
        private const val TEST_PRODUCT_DESCRIPTION = "Test product description"
        private const val TEST_PRODUCT_IMAGE = "Test product image url"
        private const val TEST_PRODUCT_PRICE_CURRENT = -1.0
        private const val TEST_PRODUCT_PRICE_OLD = -2.0
        private const val TEST_PRODUCT_MEASURE = -1
        private const val TEST_PRODUCT_MEASURE_UNIT = "Test product measure unit"
        private const val TEST_PRODUCT_DATA = -1.0
        private const val TEST_SAVE_COUNT = 0
        private const val TEST_IS_MINUS = false

        private const val testCountActual = 1
        private const val testCountExpected = 1

        private val testProductActual = Product(
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
            filterTags = immutableListOf(FilterTag(id = 6, title =  DISCOUNT_STRING))
        )

        private val testProductExpected = Product(
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
            filterTags = immutableListOf(FilterTag(id = 6, title =  DISCOUNT_STRING))
        )
    }
}