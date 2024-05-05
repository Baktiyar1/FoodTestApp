package com.example.foodtestapp.mappers.category

import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import com.example.foodtestapp.models.Category
import javax.inject.Inject

class MapCategoriesFromDomainToUi @Inject constructor(private val mapper: Mapper<DomainCategory, Category>) :
    Mapper<List<DomainCategory>, List<Category>> {
    override fun map(from: List<DomainCategory>): List<Category> = from.map(mapper::map)
}