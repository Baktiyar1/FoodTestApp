package com.example.data.cloud.mappers.tag

import com.example.data.cloud.models.CloudTag
import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapTagsFromCloudToData @Inject constructor(private val mapper: Mapper<CloudTag, DataTag>) :
    Mapper<List<CloudTag>, List<DataTag>> {
    override fun map(from: List<CloudTag>): List<DataTag> = from.map(mapper::map)
}