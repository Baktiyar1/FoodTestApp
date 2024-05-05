package com.example.foodtestapp.mappers.product

import com.example.domain.base.Mapper
import com.example.domain.models.DomainProduct
import com.example.foodtestapp.models.Product
import javax.inject.Inject

class MapProductsFromDomainToUi @Inject constructor(private val mapper: Mapper<DomainProduct, Product>) :
    Mapper<List<DomainProduct>, List<Product>> {
    override fun map(from: List<DomainProduct>): List<Product> = from.map(mapper::map)
}