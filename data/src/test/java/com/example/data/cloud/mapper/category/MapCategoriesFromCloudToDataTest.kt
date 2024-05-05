package com.example.data.cloud.mapper.category

import com.example.data.cloud.mappers.category.MapCategoriesFromCloudToData
import com.example.data.cloud.mappers.category.MapCategoryFromCloudToData
import com.example.data.cloud.models.CloudCategory
import com.example.data.models.DataCategory
import com.example.domain.base.Mapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapCategoriesFromCloudToDataTest {

    @MockK
    private lateinit var mapCategoryFromCloudToData: Mapper<CloudCategory, DataCategory>

    @MockK
    private lateinit var mapCategoriesFromCloudToData: Mapper<List<CloudCategory>, List<DataCategory>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapCategoryFromCloudToData = MapCategoryFromCloudToData()
        mapCategoriesFromCloudToData =
            MapCategoriesFromCloudToData(mapper = mapCategoryFromCloudToData)
    }

    @Test
    fun `should data categories with mapper`() {
        val actual = mapCategoriesFromCloudToData.map(from = testCategoryActual)
        val expected = testCategoryExpected

        assert(expected == actual)
    }

    companion object {
        private const val TEST_CATEGORY_ID = -1
        private const val TEST_CATEGORY_NAME = "Test category name"

        private val testCategoryActual =
            listOf(CloudCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME))
        private val testCategoryExpected =
            listOf(DataCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME))
    }
}