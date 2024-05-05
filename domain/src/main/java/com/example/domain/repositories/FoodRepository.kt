package com.example.domain.repositories

import com.example.domain.base.Result
import com.example.domain.models.DomainCategory
import com.example.domain.models.DomainProduct
import com.example.domain.models.DomainTag

interface FoodRepository {
    suspend fun getAllCategories(): Result<List<DomainCategory>>
    suspend fun getAllProducts(): Result<List<DomainProduct>>
    suspend fun getAllTags(): Result<List<DomainTag>>
}