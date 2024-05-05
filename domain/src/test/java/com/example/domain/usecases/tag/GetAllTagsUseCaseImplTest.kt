package com.example.domain.usecases.tag

import com.example.domain.base.Result
import com.example.domain.models.DomainTag
import com.example.domain.repositories.FoodRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllTagsUseCaseImplTest {

    @MockK
    private lateinit var foodRepository: FoodRepository

    @MockK
    private lateinit var getAllTagsUseCase: GetAllTagsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAllTagsUseCase = GetAllTagsUseCaseImpl(foodRepository = foodRepository)
    }

    @Test
    fun `should invoke return correct data when response is success`(): Unit = runBlocking {

        coEvery { foodRepository.getAllTags() } returns Result.Success(data = listOf(testTagActual))

        val answer = getAllTagsUseCase()
        val expend = listOf(testTagExpected)

        assert(expend == answer)
    }

    companion object {
        private const val TEST_TAG_ID = -1
        private const val TEST_TAG_NAME = "Test tag name"

        private val testTagActual = DomainTag(id = TEST_TAG_ID, name = TEST_TAG_NAME)
        private val testTagExpected = DomainTag(id = TEST_TAG_ID, name = TEST_TAG_NAME)
    }
}