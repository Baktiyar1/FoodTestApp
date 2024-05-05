package com.example.data.cloud.mappers.product

import com.example.data.cloud.models.CloudProduct
import com.example.data.models.DataProduct
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapProductsFromCloudToData @Inject constructor(private val mapper: Mapper<CloudProduct, DataProduct>) :
    Mapper<List<CloudProduct>, List<DataProduct>> {
    override fun map(from: List<CloudProduct>): List<DataProduct> = from.map(mapper::map)
}