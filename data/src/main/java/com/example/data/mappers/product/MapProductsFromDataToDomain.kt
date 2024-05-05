package com.example.data.mappers.product

import com.example.data.models.DataProduct
import com.example.domain.base.Mapper
import com.example.domain.models.DomainProduct
import javax.inject.Inject

class MapProductsFromDataToDomain @Inject constructor(private val mapper: Mapper<DataProduct, DomainProduct>) :
    Mapper<List<DataProduct>, List<DomainProduct>> {
    override fun map(from: List<DataProduct>): List<DomainProduct> = from.map(mapper::map)
}