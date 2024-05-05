package com.example.foodtestapp.mappers.product

import com.example.domain.base.Mapper
import com.example.domain.models.DomainProduct
import com.example.domain.utils.DISCOUNT_STRING
import com.example.foodtestapp.models.FilterTag
import com.example.foodtestapp.models.Product
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.collections.immutable.immutableListOf
import org.junit.Before
import org.junit.Test

class MapProductsFromDomainToUiTest {

    @MockK
    private lateinit var mapProductFromDomainToUi: Mapper<DomainProduct, Product>

    @MockK
    private lateinit var mapProductsFromDomainToUi: Mapper<List<DomainProduct>, List<Product>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapProductFromDomainToUi = MapProductFromDomainToUi()
        mapProductsFromDomainToUi = MapProductsFromDomainToUi(mapProductFromDomainToUi)
    }

    @Test
    fun `should products with mapper`() {
        val actual = mapProductsFromDomainToUi.map(from = testProductActual)
        val expected = testProductExpected

        assert(expected == actual)
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

        private val testProductActual = listOf(
            DomainProduct(
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
                tagIds = listOf(TEST_TAG_ID)
            )
        )

        private val testProductExpected = listOf(
            Product(
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
        )
    }
}