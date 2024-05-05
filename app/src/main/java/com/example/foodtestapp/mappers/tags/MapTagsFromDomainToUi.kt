package com.example.foodtestapp.mappers.tags

import com.example.domain.base.Mapper
import com.example.domain.models.DomainTag
import com.example.foodtestapp.models.Tag
import javax.inject.Inject

class MapTagsFromDomainToUi @Inject constructor(private val mapper: Mapper<DomainTag, Tag>) :
    Mapper<List<DomainTag>, List<Tag>> {
    override fun map(from: List<DomainTag>): List<Tag> = from.map(mapper::map)
}