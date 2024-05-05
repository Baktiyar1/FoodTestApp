package com.example.domain.usecases.tag

import com.example.domain.models.DomainTag
import com.example.domain.repositories.FoodRepository

class GetAllTagsUseCaseImpl(
    private val foodRepository: FoodRepository
) : GetAllTagsUseCase {

    override suspend fun invoke(): List<DomainTag> {
        val response = foodRepository.getAllTags()
        return if (response.isSuccess) response.data.orEmpty()
        else emptyList()
    }
}