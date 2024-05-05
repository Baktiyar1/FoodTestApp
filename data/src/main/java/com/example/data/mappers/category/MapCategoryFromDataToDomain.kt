package com.example.data.mappers.category

import com.example.data.models.DataCategory
import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import javax.inject.Inject

class MapCategoryFromDataToDomain @Inject constructor() : Mapper<DataCategory, DomainCategory> {
    override fun map(from: DataCategory): DomainCategory =
        from.run { DomainCategory(id = id, name = name) }
}