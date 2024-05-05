package com.example.domain.usecases.product

import com.example.domain.models.DomainProduct

interface GetAllProductsUseCase {
    suspend operator fun invoke(): List<DomainProduct>
}