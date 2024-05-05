package com.example.foodtestapp.mappers.tags

import com.example.domain.base.Mapper
import com.example.domain.models.DomainTag
import com.example.foodtestapp.models.Tag
import javax.inject.Inject

class MapTagFromDomainToUi @Inject constructor() : Mapper<DomainTag, Tag> {
    override fun map(from: DomainTag): Tag = from.run { Tag(id = id, name = name) }
}