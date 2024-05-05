package com.example.data.repository

import com.example.data.cloud.source.CloudDataSource
import com.example.data.mappers.category.MapCategoriesFromDataToDomain
import com.example.data.mappers.category.MapCategoryFromDataToDomain
import com.example.data.mappers.product.MapProductFromDataToDomain
import com.example.data.mappers.product.MapProductsFromDataToDomain
import com.example.data.mappers.tag.MapTagFromDataToDomain
import com.example.data.mappers.tag.MapTagsFromDataToDomain
import com.example.data.models.DataCategory
import com.example.data.models.DataProduct
import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import com.example.domain.models.DomainProduct
import com.example.domain.models.DomainTag
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FoodRepositoryImplTest {

    @MockK
    private lateinit var cloudDataSource: CloudDataSource

    @MockK
    private lateinit var mapCategoryFromDataToDomain: Mapper<DataCategory, DomainCategory>

    @MockK
    private lateinit var mapProductFromDataToDomain: Mapper<DataProduct, DomainProduct>

    @MockK
    private lateinit var mapTagFromDataToDomain: Mapper<DataTag, DomainTag>

    @MockK
    private lateinit var mapCategoriesFromDataToDomain: Mapper<List<DataCategory>, List<DomainCategory>>

    @MockK
    private lateinit var mapProductsFromDataToDomain: Mapper<List<DataProduct>, List<DomainProduct>>

    @MockK
    private lateinit var mapTagsFromDataToDomain: Mapper<List<DataTag>, List<DomainTag>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mapCategoryFromDataToDomain = MapCategoryFromDataToDomain()
        mapCategoriesFromDataToDomain = MapCategoriesFromDataToDomain(mapCategoryFromDataToDomain)

        mapProductFromDataToDomain = MapProductFromDataToDomain()
        mapProductsFromDataToDomain = MapProductsFromDataToDomain(mapProductFromDataToDomain)

        mapTagFromDataToDomain = MapTagFromDataToDomain()
        mapTagsFromDataToDomain = MapTagsFromDataToDomain(mapTagFromDataToDomain)
    }

    @Test
    fun `should return correct data categories`() = runBlocking {
        coEvery { cloudDataSource.getAllCategories() } returns testCategoryActual

        val actual = mapCategoriesFromDataToDomain.map(from = cloudDataSource.getAllCategories())
        val expend = testCategoryExpected

        assert(expend == actual)
    }

    @Test
    fun `should return correct data products`() = runBlocking {
        coEvery { cloudDataSource.getAllProducts() } returns testProductActual

        val actual = mapProductsFromDataToDomain.map(from = cloudDataSource.getAllProducts())
        val expend = testProductExpected

        assert(expend == actual)
    }

    @Test
    fun `should return correct data tags`() = runBlocking {
        coEvery { cloudDataSource.getAllTags() } returns testTagActual

        val actual = mapTagsFromDataToDomain.map(from = cloudDataSource.getAllTags())
        val expend = testTagExpected

        assert(expend == actual)
    }

    companion object {
        private const val TEST_CATEGORY_ID = -111
        private const val TEST_CATEGORY_NAME = "Test category name"
        private const val TEST_TAG_ID = -11
        private const val TEST_TAG_NAME = "Test tag name"
        private const val TEST_PRODUCT_ID = -1
        private const val TEST_PRODUCT_NAME = "Test product name"
        private const val TEST_PRODUCT_DESCRIPTION = "Test product description"
        private const val TEST_PRODUCT_IMAGE = "Test product image url"
        private const val TEST_PRODUCT_PRICE_CURRENT = -1.0
        private const val TEST_PRODUCT_PRICE_OLD = -2.0
        private const val TEST_PRODUCT_MEASURE = -1
        private const val TEST_PRODUCT_MEASURE_UNIT = "Test product measure unit"
        private const val TEST_PRODUCT_DATA = -1.0

        private val testCategoryActual =
            listOf(DataCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME))
        private val testCategoryExpected =
            listOf(DomainCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME))

        private val testTagActual = listOf(DataTag(id = TEST_TAG_ID, name = TEST_TAG_NAME))
        private val testTagExpected = listOf(DomainTag(id = TEST_TAG_ID, name = TEST_TAG_NAME))

        private val testProductActual = listOf(
            DataProduct(
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
    }
}