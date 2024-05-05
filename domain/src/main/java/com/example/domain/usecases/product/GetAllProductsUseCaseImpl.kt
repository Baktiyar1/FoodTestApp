package com.example.domain.usecases.product

import com.example.domain.models.DomainProduct
import com.example.domain.repositories.FoodRepository

class GetAllProductsUseCaseImpl(private val foodRepository: FoodRepository) : GetAllProductsUseCase {
    override suspend fun invoke(): List<DomainProduct> {
        val result = foodRepository.getAllProducts()
        return when (result.isFailed) {
            false -> result.data ?: emptyList()
            true -> emptyList()
        }
    }
}