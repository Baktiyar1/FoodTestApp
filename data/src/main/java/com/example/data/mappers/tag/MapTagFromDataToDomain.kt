package com.example.data.mappers.tag

import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import com.example.domain.models.DomainTag
import javax.inject.Inject

class MapTagFromDataToDomain @Inject constructor() : Mapper<DataTag, DomainTag> {
    override fun map(from: DataTag): DomainTag = from.run { DomainTag(id = id, name = name) }
}