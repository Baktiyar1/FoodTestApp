package com.example.foodtestapp.mappers.category

import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import com.example.foodtestapp.models.Category
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapCategoriesFromDomainToUiTest {

    @MockK
    private lateinit var mapCategoryFromDomainToUi: Mapper<DomainCategory, Category>

    @MockK
    private lateinit var mapCategoriesFromDomainToUi: Mapper<List<DomainCategory>, List<Category>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapCategoryFromDomainToUi = MapCategoryFromDomainToUi()
        mapCategoriesFromDomainToUi = MapCategoriesFromDomainToUi(mapCategoryFromDomainToUi)
    }

    @Test
    fun `should category with mapper`() {
        val actual = mapCategoriesFromDomainToUi.map(from = testCategoryActual)
        val expected = testCategoryExpected

        assert(expected == actual)
    }

    companion object {
        private const val TEST_CATEGORY_ID = -1
        private const val TEST_CATEGORY_NAME = "Test category name"

        private val testCategoryActual =
            listOf(DomainCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME))
        private val testCategoryExpected =
            listOf(Category(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME))
    }
}