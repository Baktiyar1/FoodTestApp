package com.example.domain.usecases.category

import com.example.domain.models.DomainCategory
import com.example.domain.repositories.FoodRepository

class GetAllCategoriesUseCaseImpl(private val foodRepository: FoodRepository) : GetAllCategoriesUseCase {
    override suspend fun invoke(): List<DomainCategory> {
        val result = foodRepository.getAllCategories()
        return when (result.isFailed) {
            false -> result.data ?: emptyList()
            true -> emptyList()
        }
    }
}