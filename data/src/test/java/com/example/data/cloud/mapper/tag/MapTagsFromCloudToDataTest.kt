package com.example.data.cloud.mapper.tag

import com.example.data.cloud.mappers.tag.MapTagFromCloudToData
import com.example.data.cloud.mappers.tag.MapTagsFromCloudToData
import com.example.data.cloud.models.CloudTag
import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapTagsFromCloudToDataTest {

    @MockK
    private lateinit var mapTagFromCloudToData: Mapper<CloudTag, DataTag>

    @MockK
    private lateinit var mapTagsFromCloudToData: Mapper<List<CloudTag>, List<DataTag>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapTagFromCloudToData = MapTagFromCloudToData()
        mapTagsFromCloudToData = MapTagsFromCloudToData(mapper = mapTagFromCloudToData)
    }

    @Test
    fun `should data tags with mapper`() {
        val actual = mapTagsFromCloudToData.map(from = testTagActual)
        val expected = testTagExpected

        assert(expected == actual)
    }

    companion object {
        private const val TEST_TAG_ID = -11
        private const val TEST_TAG_NAME = "Test tag name"

        private val testTagActual = listOf(CloudTag(id = TEST_TAG_ID, name = TEST_TAG_NAME))
        private val testTagExpected = listOf(DataTag(id = TEST_TAG_ID, name = TEST_TAG_NAME))
    }
}