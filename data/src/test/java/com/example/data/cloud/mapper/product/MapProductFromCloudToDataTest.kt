package com.example.data.cloud.mapper.product

import com.example.data.cloud.mappers.product.MapProductFromCloudToData
import com.example.data.cloud.models.CloudProduct
import com.example.data.models.DataProduct
import com.example.domain.base.Mapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapProductFromCloudToDataTest {

    @MockK
    private lateinit var mapProductFromCloudToData: Mapper<CloudProduct, DataProduct>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapProductFromCloudToData = MapProductFromCloudToData()
    }

    @Test
    fun `should data product with mapper`() {
        val actual = mapProductFromCloudToData.map(from = testProductActual)
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
        private const val TEST_PRODUCT_CLOUD_PRICE_CURRENT = -100.0
        private const val TEST_PRODUCT_DATA_PRICE_CURRENT = -1.0
        private const val TEST_PRODUCT_CLOUD_PRICE_OLD = -200.0
        private const val TEST_PRODUCT_DATA_PRICE_OLD = -2.0
        private const val TEST_PRODUCT_MEASURE = -1
        private const val TEST_PRODUCT_MEASURE_UNIT = "Test product measure unit"
        private const val TEST_PRODUCT_DATA = -1.0

        private val testProductActual = CloudProduct(
            id = TEST_PRODUCT_ID,
            name = TEST_PRODUCT_NAME,
            description = TEST_PRODUCT_DESCRIPTION,
            image = TEST_PRODUCT_IMAGE,
            priceCurrent = TEST_PRODUCT_CLOUD_PRICE_CURRENT,
            priceOld = TEST_PRODUCT_CLOUD_PRICE_OLD,
            categoryId = TEST_CATEGORY_ID,
            measure = TEST_PRODUCT_MEASURE,
            measureUnit = TEST_PRODUCT_MEASURE_UNIT,
            energy = TEST_PRODUCT_DATA,
            proteins = TEST_PRODUCT_DATA,
            fats = TEST_PRODUCT_DATA,
            carbohydrates = TEST_PRODUCT_DATA,
            tagIds = listOf(TEST_TAG_ID)
        )

        private val testProductExpected = DataProduct(
            id = TEST_PRODUCT_ID,
            name = TEST_PRODUCT_NAME,
            description = TEST_PRODUCT_DESCRIPTION,
            image = TEST_PRODUCT_IMAGE,
            priceCurrent = TEST_PRODUCT_DATA_PRICE_CURRENT,
            priceOld = TEST_PRODUCT_DATA_PRICE_OLD,
            categoryId = TEST_CATEGORY_ID,
            measure = TEST_PRODUCT_MEASURE,
            measureUnit = TEST_PRODUCT_MEASURE_UNIT,
            energy = TEST_PRODUCT_DATA,
            proteins = TEST_PRODUCT_DATA,
            fats = TEST_PRODUCT_DATA,
            carbohydrates = TEST_PRODUCT_DATA,
            tagIds = listOf(TEST_TAG_ID)
        )
    }
}