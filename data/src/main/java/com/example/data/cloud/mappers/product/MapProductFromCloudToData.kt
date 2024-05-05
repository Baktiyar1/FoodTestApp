package com.example.data.cloud.mappers.product

import com.example.data.cloud.models.CloudProduct
import com.example.data.models.DataProduct
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapProductFromCloudToData @Inject constructor() : Mapper<CloudProduct, DataProduct> {
    override fun map(from: CloudProduct): DataProduct = from.run {
        DataProduct(
            id = id,
            name = name,
            description = description,
            image = image,
            priceCurrent = priceCurrent / 100,
            priceOld = priceOld?.div(100),
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