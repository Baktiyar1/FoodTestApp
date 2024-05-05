package com.example.data.mapper.tag

import com.example.data.mappers.tag.MapTagFromDataToDomain
import com.example.data.mappers.tag.MapTagsFromDataToDomain
import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import com.example.domain.models.DomainTag
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapTagsFromCloudToDataTest {

    @MockK
    private lateinit var mapTagFromDataToDomain: Mapper<DataTag, DomainTag>

    @MockK
    private lateinit var mapTagsFromDataToDomain: Mapper<List<DataTag>, List<DomainTag>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapTagFromDataToDomain = MapTagFromDataToDomain()
        mapTagsFromDataToDomain = MapTagsFromDataToDomain(mapper = mapTagFromDataToDomain)
    }

    @Test
    fun `should domain tags with mapper`() {
        val actual = mapTagsFromDataToDomain.map(from = testTagActual)
        val expected = testTagExpected

        assert(expected == actual)
    }

    companion object {
        private const val TEST_TAG_ID = -11
        private const val TEST_TAG_NAME = "Test tag name"

        private val testTagActual = listOf(DataTag(id = TEST_TAG_ID, name = TEST_TAG_NAME))
        private val testTagExpected = listOf(DomainTag(id = TEST_TAG_ID, name = TEST_TAG_NAME))
    }
}