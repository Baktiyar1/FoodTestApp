package com.example.data.cloud.mappers.category

import com.example.data.cloud.models.CloudCategory
import com.example.data.models.DataCategory
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapCategoriesFromCloudToData @Inject constructor(private val mapper: Mapper<CloudCategory, DataCategory>) :
    Mapper<List<CloudCategory>, List<DataCategory>> {
    override fun map(from: List<CloudCategory>): List<DataCategory> = from.map(mapper::map)
}