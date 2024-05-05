package com.example.data.mappers.product

import com.example.data.models.DataProduct
import com.example.domain.base.Mapper
import com.example.domain.models.DomainProduct
import javax.inject.Inject

class MapProductFromDomainToData @Inject constructor() : Mapper<DomainProduct, DataProduct> {
    override fun map(from: DomainProduct): DataProduct = from.run {
        DataProduct(
            id = id, name = name,
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
            tagIds = tagIds,
        )
    }
}