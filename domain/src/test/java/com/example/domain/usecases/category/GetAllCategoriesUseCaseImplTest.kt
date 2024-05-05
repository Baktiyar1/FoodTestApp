package com.example.domain.usecases.category

import com.example.domain.base.Result
import com.example.domain.models.DomainCategory
import com.example.domain.repositories.FoodRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllCategoriesUseCaseImplTest {

    @MockK
    private lateinit var foodRepository: FoodRepository

    @MockK
    private lateinit var getAllCategoriesUseCase: GetAllCategoriesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAllCategoriesUseCase = GetAllCategoriesUseCaseImpl(foodRepository = foodRepository)
    }

    @Test
    fun `should invoke return correct data when response is success`(): Unit = runBlocking {

        coEvery { foodRepository.getAllCategories() } returns Result.Success(
            data = listOf(testCategoryActual)
        )

        val answer = getAllCategoriesUseCase()
        val expend = listOf(testCategoryExpected)

        assert(expend == answer)
    }

    companion object {
        private const val TEST_CATEGORY_ID = -1
        private const val TEST_CATEGORY_NAME = "Test category name"

        private val testCategoryActual =
            DomainCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME)
        private val testCategoryExpected =
            DomainCategory(id = TEST_CATEGORY_ID, name = TEST_CATEGORY_NAME)
    }
}