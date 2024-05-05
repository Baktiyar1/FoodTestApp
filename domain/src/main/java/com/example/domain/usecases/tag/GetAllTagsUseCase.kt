package com.example.domain.usecases.tag

import com.example.domain.models.DomainTag

interface GetAllTagsUseCase {
    suspend operator fun invoke(): List<DomainTag>
}