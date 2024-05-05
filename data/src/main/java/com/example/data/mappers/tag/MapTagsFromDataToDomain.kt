package com.example.data.mappers.tag

import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import com.example.domain.models.DomainTag
import javax.inject.Inject

class MapTagsFromDataToDomain @Inject constructor(private val mapper: Mapper<DataTag, DomainTag>) :
    Mapper<List<DataTag>, List<DomainTag>> {
    override fun map(from: List<DataTag>): List<DomainTag> = from.map(mapper::map)
}