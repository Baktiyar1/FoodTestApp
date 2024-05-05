package com.example.data.cloud.mappers.tag

import com.example.data.cloud.models.CloudTag
import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapTagFromCloudToData @Inject constructor() : Mapper<CloudTag, DataTag> {
    override fun map(from: CloudTag): DataTag = from.run { DataTag(id = id, name = name) }
}