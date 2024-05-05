package com.example.data.cloud.mapper.category

import com.example.data.cloud.mappers.category.MapCategoryFromCloudToData
import com.example.data.cloud.models.CloudCategory
import com.example.data.models.DataCategory
import com.example.domain.base.Mapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapCategoryFromCloudToDataTest {

    @MockK
    private lateinit var mapCategoryFromCloudToData: Mapper<CloudCategory, DataCategory>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapCategoryFromCloudToData = MapCategoryFromCloudToData()
    }

    @Test
    fun `should data category with mapper`() {
        val actual = mapCategoryFromCloudToData.map(from = testCategoryActual)
        val expected = testCategoryExpected

        assert(expected == actual)
    }

    companion object {
        private const val TEST_CATEGORY_ID = -1
        private const val TEST_CATEGORY_NAME = "Test category name"

        private val testCategoryActual =
            CloudCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME)
        private val testCategoryExpected =
            DataCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME)
    }
}