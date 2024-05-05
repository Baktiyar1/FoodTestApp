package com.example.foodtestapp.mappers.product

import com.example.domain.base.Mapper
import com.example.domain.models.DomainProduct
import com.example.domain.utils.ACUTE_STRING
import com.example.domain.utils.DEFAULT_OLD_PRICE
import com.example.domain.utils.DISCOUNT_STRING
import com.example.domain.utils.WITHOUT_MEAT_STRING
import com.example.foodtestapp.models.FilterTag
import com.example.foodtestapp.models.Product
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class MapProductFromDomainToUi @Inject constructor() : Mapper<DomainProduct, Product> {
    override fun map(from: DomainProduct): Product = from.run {
        val filterTags = tagIds.filter { it == 2 || it == 4 }.map {
            when (it) {
                2 -> FilterTag(id = 2, title = WITHOUT_MEAT_STRING)
                4 -> FilterTag(id = 4, title = ACUTE_STRING)
                else -> throw IllegalArgumentException("Unsupported tag id: $it")
            }
        }.toMutableList()
            .apply { if (priceOld != null) add(FilterTag(id = 6, title = DISCOUNT_STRING)) }
        Product(
            id = id,
            name = name,
            description = description,
            image = image,
            priceCurrent = priceCurrent,
            priceOld = priceOld ?: DEFAULT_OLD_PRICE,
            categoryId = categoryId,
            measure = measure,
            measureUnit = measureUnit,
            energy = energy,
            proteins = proteins,
            fats = fats,
            carbohydrates = carbohydrates,
            tagIds = tagIds.toImmutableList(),
            filterTags = filterTags.toImmutableList()
        )
    }
}