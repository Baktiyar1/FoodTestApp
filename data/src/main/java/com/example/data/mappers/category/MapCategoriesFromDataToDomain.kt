package com.example.data.mappers.category

import com.example.data.models.DataCategory
import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import javax.inject.Inject

class MapCategoriesFromDataToDomain @Inject constructor(private val mapper: Mapper<DataCategory, DomainCategory>) :
    Mapper<List<DataCategory>, List<DomainCategory>> {
    override fun map(from: List<DataCategory>): List<DomainCategory> = from.map(mapper::map)
}