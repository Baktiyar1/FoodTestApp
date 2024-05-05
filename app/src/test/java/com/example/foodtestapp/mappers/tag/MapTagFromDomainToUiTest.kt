package com.example.foodtestapp.mappers.tag

import com.example.domain.base.Mapper
import com.example.domain.models.DomainTag
import com.example.foodtestapp.mappers.tags.MapTagFromDomainToUi
import com.example.foodtestapp.models.Tag
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapTagFromDomainToUiTest {

    @MockK
    private lateinit var mapTagFromDomainToUi: Mapper<DomainTag, Tag>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapTagFromDomainToUi = MapTagFromDomainToUi()
    }

    @Test
    fun `should tag with mapper`() {
        val actual = mapTagFromDomainToUi.map(from = testTagActual)
        val expected = testTagExpected

        assert(expected == actual)
    }

    companion object {
        private const val TEST_TAG_ID = -11
        private const val TEST_TAG_NAME = "Test tag name"

        private val testTagActual = DomainTag(id = TEST_TAG_ID, name = TEST_TAG_NAME)
        private val testTagExpected = Tag(id = TEST_TAG_ID, name = TEST_TAG_NAME)
    }
}