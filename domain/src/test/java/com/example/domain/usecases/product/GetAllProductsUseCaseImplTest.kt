package com.example.domain.usecases.product

import com.example.domain.base.Result
import com.example.domain.models.DomainProduct
import com.example.domain.repositories.FoodRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllProductsUseCaseImplTest {

    @MockK
    private lateinit var foodRepository: FoodRepository

    @MockK
    private lateinit var getAllProductsUseCase: GetAllProductsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAllProductsUseCase = GetAllProductsUseCaseImpl(foodRepository = foodRepository)
    }

    @Test
    fun `should invoke return correct data when response is success`(): Unit = runBlocking {

        coEvery { foodRepository.getAllProducts() } returns Result.Success(
            data = listOf(testProductActual)
        )

        val answer = getAllProductsUseCase()
        val expend = listOf(testProductExpected)

        assert(expend == answer)
    }

    companion object {
        private const val TEST_PRODUCT_ID = -1
        private const val TEST_PRODUCT_NAME = "Test product name"
        private const val TEST_PRODUCT_DESCRIPTION = "Test product description"
        private const val TEST_PRODUCT_IMAGE = "Test product image url"
        private const val TEST_PRODUCT_PRICE_CURRENT = -1.0
        private const val TEST_PRODUCT_PRICE_OLD = -2.0
        private const val TEST_PRODUCT_CATEGORY_ID = -1
        private const val TEST_PRODUCT_MEASURE = -1
        private const val TEST_PRODUCT_MEASURE_UNIT = "Test product measure unit"
        private const val TEST_PRODUCT_DATA = -1.0
        private const val TEST_PRODUCT_TAG_ID = -1

        private val testProductActual = DomainProduct(
            id = TEST_PRODUCT_ID,
            name = TEST_PRODUCT_NAME,
            description = TEST_PRODUCT_DESCRIPTION,
            image = TEST_PRODUCT_IMAGE,
            priceCurrent = TEST_PRODUCT_PRICE_CURRENT,
            priceOld = TEST_PRODUCT_PRICE_OLD,
            categoryId = TEST_PRODUCT_CATEGORY_ID,
            measure = TEST_PRODUCT_MEASURE,
            measureUnit = TEST_PRODUCT_MEASURE_UNIT,
            energy = TEST_PRODUCT_DATA,
            proteins = TEST_PRODUCT_DATA,
            fats = TEST_PRODUCT_DATA,
            carbohydrates = TEST_PRODUCT_DATA,
            tagIds = listOf(TEST_PRODUCT_TAG_ID)
        )

        private val testProductExpected = DomainProduct(
            id = TEST_PRODUCT_ID,
            name = TEST_PRODUCT_NAME,
            description = TEST_PRODUCT_DESCRIPTION,
            image = TEST_PRODUCT_IMAGE,
            priceCurrent = TEST_PRODUCT_PRICE_CURRENT,
            priceOld = TEST_PRODUCT_PRICE_OLD,
            categoryId = TEST_PRODUCT_CATEGORY_ID,
            measure = TEST_PRODUCT_MEASURE,
            measureUnit = TEST_PRODUCT_MEASURE_UNIT,
            energy = TEST_PRODUCT_DATA,
            proteins = TEST_PRODUCT_DATA,
            fats = TEST_PRODUCT_DATA,
            carbohydrates = TEST_PRODUCT_DATA,
            tagIds = listOf(TEST_PRODUCT_TAG_ID)
        )
    }
}