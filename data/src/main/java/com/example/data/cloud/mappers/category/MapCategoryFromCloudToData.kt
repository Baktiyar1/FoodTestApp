package com.example.data.cloud.mappers.category

import com.example.data.cloud.models.CloudCategory
import com.example.data.models.DataCategory
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapCategoryFromCloudToData @Inject constructor() : Mapper<CloudCategory, DataCategory> {
    override fun map(from: CloudCategory): DataCategory = from.run {
        DataCategory(id = id, name = name)
    }
}