package com.example.foodtestapp.mappers.tag

import com.example.domain.base.Mapper
import com.example.domain.models.DomainTag
import com.example.foodtestapp.mappers.tags.MapTagFromDomainToUi
import com.example.foodtestapp.mappers.tags.MapTagsFromDomainToUi
import com.example.foodtestapp.models.Tag
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapTagsFromDomainToUiTest {

    @MockK
    private lateinit var mapTagFromDomainToUi: Mapper<DomainTag, Tag>

    @MockK
    private lateinit var mapTagsFromDomainToUi: Mapper<List<DomainTag>, List<Tag>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapTagFromDomainToUi = MapTagFromDomainToUi()
        mapTagsFromDomainToUi = MapTagsFromDomainToUi(mapTagFromDomainToUi)
    }

    @Test
    fun `should tags with mapper`() {
        val actual = mapTagsFromDomainToUi.map(from = testTagActual)
        val expected = testTagExpected

        assert(expected == actual)
    }

    companion object {
        private const val TEST_TAG_ID = -11
        private const val TEST_TAG_NAME = "Test tag name"

        private val testTagActual = listOf(DomainTag(id = TEST_TAG_ID, name = TEST_TAG_NAME))
        private val testTagExpected = listOf(Tag(id = TEST_TAG_ID, name = TEST_TAG_NAME))
    }
}