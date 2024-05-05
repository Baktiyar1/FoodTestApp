package com.example.foodtestapp.mappers.category

import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import com.example.foodtestapp.models.Category
import javax.inject.Inject

class MapCategoryFromDomainToUi @Inject constructor() : Mapper<DomainCategory, Category> {
    override fun map(from: DomainCategory): Category = from.run { Category(id = id, name = name) }
}