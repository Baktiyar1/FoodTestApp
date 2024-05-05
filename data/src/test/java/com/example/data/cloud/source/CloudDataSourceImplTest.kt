package com.example.data.cloud.source

import com.example.data.cloud.mappers.category.MapCategoriesFromCloudToData
import com.example.data.cloud.mappers.category.MapCategoryFromCloudToData
import com.example.data.cloud.mappers.product.MapProductFromCloudToData
import com.example.data.cloud.mappers.product.MapProductsFromCloudToData
import com.example.data.cloud.mappers.tag.MapTagFromCloudToData
import com.example.data.cloud.mappers.tag.MapTagsFromCloudToData
import com.example.data.cloud.models.CloudCategory
import com.example.data.cloud.models.CloudProduct
import com.example.data.cloud.models.CloudTag
import com.example.data.cloud.services.FoodService
import com.example.data.models.DataCategory
import com.example.data.models.DataProduct
import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CloudDataSourceImplTest {

    @MockK
    private lateinit var foodService: FoodService

    @MockK
    private lateinit var mapCategoryFromCloudToData: Mapper<CloudCategory, DataCategory>

    @MockK
    private lateinit var mapProductFromCloudToData: Mapper<CloudProduct, DataProduct>

    @MockK
    private lateinit var mapTagFromCloudToData: Mapper<CloudTag, DataTag>

    @MockK
    private lateinit var mapCategoriesFromCloudToData: Mapper<List<CloudCategory>, List<DataCategory>>

    @MockK
    private lateinit var mapProductsFromCloudToData: Mapper<List<CloudProduct>, List<DataProduct>>

    @MockK
    private lateinit var mapTagsFromCloudToData: Mapper<List<CloudTag>, List<DataTag>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mapCategoryFromCloudToData = MapCategoryFromCloudToData()
        mapCategoriesFromCloudToData = MapCategoriesFromCloudToData(mapCategoryFromCloudToData)

        mapProductFromCloudToData = MapProductFromCloudToData()
        mapProductsFromCloudToData = MapProductsFromCloudToData(mapProductFromCloudToData)

        mapTagFromCloudToData = MapTagFromCloudToData()
        mapTagsFromCloudToData = MapTagsFromCloudToData(mapTagFromCloudToData)
    }

    @Test
    fun `should return correct data when response is success categories`() = runBlocking {
        coEvery { foodService.getAllCategories() } returns Response.success(
            listOf(testCategoryActual)
        )

        val actual =
            mapCategoriesFromCloudToData.map(from = foodService.getAllCategories().body().orEmpty())
        val expend = listOf(testCategoryExpected)

        assert(expend == actual)
    }

    @Test
    fun `should return correct data when response is success products`() = runBlocking {
        coEvery { foodService.getAllProducts() } returns Response.success(listOf(testProductActual))

        val actual =
            mapProductsFromCloudToData.map(from = foodService.getAllProducts().body().orEmpty())
        val expend = listOf(testProductExpected)

        assert(expend == actual)
    }

    @Test
    fun `should return correct data when response is success tags`() = runBlocking {
        coEvery { foodService.getAllTags() } returns Response.success(listOf(testTagActual))

        val actual = mapTagsFromCloudToData.map(from = foodService.getAllTags().body().orEmpty())
        val expend = listOf(testTagExpected)

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
        private const val TEST_PRODUCT_CLOUD_PRICE_CURRENT = -100.0
        private const val TEST_PRODUCT_DATA_PRICE_CURRENT = -1.0
        private const val TEST_PRODUCT_CLOUD_PRICE_OLD = -200.0
        private const val TEST_PRODUCT_DATA_PRICE_OLD = -2.0
        private const val TEST_PRODUCT_MEASURE = -1
        private const val TEST_PRODUCT_MEASURE_UNIT = "Test product measure unit"
        private const val TEST_PRODUCT_DATA = -1.0

        private val testCategoryExpected =
            DataCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME)
        private val testCategoryActual =
            CloudCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME)

        private val testTagActual = CloudTag(id = TEST_TAG_ID, name = TEST_TAG_NAME)
        private val testTagExpected = DataTag(id = TEST_TAG_ID, name = TEST_TAG_NAME)

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