package com.example.foodtestapp.mappers.category

import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import com.example.foodtestapp.models.Category
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapCategoryFromDomainToUiTest {

    @MockK
    private lateinit var mapCategoryFromDomainToUi: Mapper<DomainCategory, Category>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapCategoryFromDomainToUi = MapCategoryFromDomainToUi()
    }

    @Test
    fun `should categories with mapper`() {
        val actual = mapCategoryFromDomainToUi.map(from = testCategoryActual)
        val expected = testCategoryExpected

        assert(expected == actual)
    }

    companion object {
        private const val TEST_CATEGORY_ID = -1
        private const val TEST_CATEGORY_NAME = "Test category name"

        private val testCategoryActual =
            DomainCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME)
        private val testCategoryExpected =
            Category(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME)
    }
}