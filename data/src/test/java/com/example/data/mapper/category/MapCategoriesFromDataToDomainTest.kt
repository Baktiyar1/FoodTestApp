package com.example.data.mapper.category

import com.example.data.mappers.category.MapCategoryFromDataToDomain
import com.example.data.models.DataCategory
import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapCategoriesFromDataToDomainTest {

    @MockK
    private lateinit var mapCategoryFromDataToDomain: Mapper<DataCategory, DomainCategory>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapCategoryFromDataToDomain = MapCategoryFromDataToDomain()
    }

    @Test
    fun `should domain category with mapper`() {
        val actual = mapCategoryFromDataToDomain.map(from = testCategoryActual)
        val expected = testCategoryExpected

        assert(expected == actual)
    }

    companion object {
        private const val TEST_CATEGORY_ID = -1
        private const val TEST_CATEGORY_NAME = "Test category name"

        private val testCategoryActual =
            DataCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME)
        private val testCategoryExpected =
            DomainCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME)
    }
}