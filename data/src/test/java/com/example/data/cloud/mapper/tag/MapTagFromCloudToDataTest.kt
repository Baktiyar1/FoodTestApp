package com.example.data.cloud.mapper.tag

import com.example.data.cloud.mappers.tag.MapTagFromCloudToData
import com.example.data.cloud.models.CloudTag
import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MapTagFromCloudToDataTest {

    @MockK
    private lateinit var mapTagFromCloudToData: Mapper<CloudTag, DataTag>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapTagFromCloudToData = MapTagFromCloudToData()
    }

    @Test
    fun `should data tag with mapper`() {
        val actual = mapTagFromCloudToData.map(from = testTagActual)
        val expected = testTagExpected

        assert(expected == actual)
    }

    companion object {
        private const val TEST_TAG_ID = -11
        private const val TEST_TAG_NAME = "Test tag name"

        private val testTagActual = CloudTag(id = TEST_TAG_ID, name = TEST_TAG_NAME)
        private val testTagExpected = DataTag(id = TEST_TAG_ID, name = TEST_TAG_NAME)
    }
}