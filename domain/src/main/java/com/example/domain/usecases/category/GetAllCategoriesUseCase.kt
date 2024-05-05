package com.example.domain.usecases.category

import com.example.domain.models.DomainCategory

interface GetAllCategoriesUseCase {
    suspend operator fun invoke(): List<DomainCategory>
}