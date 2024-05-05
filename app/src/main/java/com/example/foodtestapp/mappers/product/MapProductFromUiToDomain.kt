package com.example.foodtestapp.mappers.product

import com.example.domain.base.Mapper
import com.example.domain.models.DomainProduct
import com.example.foodtestapp.models.Product
import javax.inject.Inject

class MapProductFromUiToDomain @Inject constructor() : Mapper<Product, DomainProduct> {
    override fun map(from: Product): DomainProduct = from.run {
        DomainProduct(
            id = id,
            name = name,
            description = description,
            image = image,
            priceCurrent = priceCurrent,
            priceOld = priceOld,
            categoryId = categoryId,
            measure = measure,
            measureUnit = measureUnit,
            energy = energy,
            proteins = proteins,
            fats = fats,
            carbohydrates = carbohydrates,
            tagIds = tagIds
        )
    }
}