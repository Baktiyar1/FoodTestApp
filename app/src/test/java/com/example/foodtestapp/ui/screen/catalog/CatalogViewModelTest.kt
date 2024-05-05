package com.example.foodtestapp.ui.screen.catalog

import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import com.example.domain.models.DomainProduct
import com.example.domain.models.DomainTag
import com.example.domain.usecases.category.GetAllCategoriesUseCase
import com.example.domain.usecases.product.GetAllProductsUseCase
import com.example.domain.usecases.tag.GetAllTagsUseCase
import com.example.domain.utils.ACUTE_STRING
import com.example.domain.utils.DISCOUNT_STRING
import com.example.domain.utils.EMPTY_STRING
import com.example.domain.utils.WITHOUT_MEAT_STRING
import com.example.foodtestapp.models.Category
import com.example.foodtestapp.models.FilterTag
import com.example.foodtestapp.models.Product
import com.example.foodtestapp.models.Tag
import com.example.foodtestapp.ui.product.ProductCountRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CatalogViewModelTest {

    private lateinit var viewModel: CatalogViewModel
    private val getAllCategoriesUseCase = mockk<GetAllCategoriesUseCase>()
    private val getAllProductsUseCase = mockk<GetAllProductsUseCase>()
    private val getAllTagsUseCase = mockk<GetAllTagsUseCase>()
    private val mapProductFromDomainToUi = mockk<Mapper<DomainProduct, Product>>()
    private val mapCategoriesFromDomainToUi = mockk<Mapper<List<DomainCategory>, List<Category>>>()
    private val mapTagsFromDomainToUi = mockk<Mapper<List<DomainTag>, List<Tag>>>()
    private val productCountRepository = mockk<ProductCountRepository>()
    private lateinit var testProduct: Product
    private lateinit var testDomainCategories: List<DomainCategory>
    private lateinit var testDomainTags: List<DomainTag>
    private lateinit var testDomainProducts: List<DomainProduct>
    private lateinit var testFilterTags: Map<Int, FilterTag>

    @Before
    fun setUp(): Unit = runBlocking {
        testFilterTags = mapOf(
            2 to FilterTag(id = 2, title = WITHOUT_MEAT_STRING),
            4 to FilterTag(id = 4, title = ACUTE_STRING),
            6 to FilterTag(id = 6, title = DISCOUNT_STRING),
        )
        testProduct = Product(
            id = TEST_PRODUCT_ID,
            name = TEST_PRODUCT_NAME,
            description = TEST_PRODUCT_DESCRIPTION,
            image = EMPTY_STRING,
            priceCurrent = TEST_PRODUCT_PRICE_CURRENT,
            priceOld = TEST_PRODUCT_PRICE_OLD,
            categoryId = TEST_CATEGORY_ID,
            measure = TEST_PRODUCT_MEASURE,
            measureUnit = TEST_PRODUCT_MEASURE_UNIT,
            energy = TEST_PRODUCT_ENERGY,
            proteins = TEST_PRODUCT_ENERGY,
            fats = TEST_PRODUCT_ENERGY,
            carbohydrates = TEST_PRODUCT_ENERGY,
            tagIds = listOf(TEST_TAG_ID).toImmutableList(),
            filterTags = testFilterTags.values.toImmutableList()
        )
        val testCategories = listOf(Category(TEST_CATEGORY_ID, TEST_CATEGORY_NAME))
        val testTags = listOf(Tag(TEST_TAG_ID, TEST_TAG_NAME))
        testDomainCategories = listOf(DomainCategory(TEST_CATEGORY_ID, TEST_CATEGORY_NAME))
        testDomainTags = listOf(DomainTag(TEST_TAG_ID, TEST_TAG_NAME))
        testDomainProducts = listOf(
            DomainProduct(
                id = TEST_PRODUCT_ID,
                name = TEST_PRODUCT_NAME,
                description = TEST_PRODUCT_DESCRIPTION,
                image = EMPTY_STRING,
                priceCurrent = TEST_PRODUCT_PRICE_CURRENT,
                priceOld = TEST_PRODUCT_PRICE_OLD,
                categoryId = TEST_CATEGORY_ID,
                measure = TEST_PRODUCT_MEASURE,
                measureUnit = TEST_PRODUCT_MEASURE_UNIT,
                energy = TEST_PRODUCT_ENERGY,
                proteins = TEST_PRODUCT_ENERGY,
                fats = TEST_PRODUCT_ENERGY,
                carbohydrates = TEST_PRODUCT_ENERGY,
                tagIds = listOf(TEST_TAG_ID)
            )
        )

        every { productCountRepository.filterTags } returns testFilterTags

        viewModel = CatalogViewModel(
            getAllCategoriesUseCase = getAllCategoriesUseCase,
            getAllProductsUseCase = getAllProductsUseCase,
            getAllTagsUseCase = getAllTagsUseCase,
            mapProductFromDomainToUi = mapProductFromDomainToUi,
            mapCategoriesFromDomainToUi = mapCategoriesFromDomainToUi,
            mapTagsFromDomainToUi = mapTagsFromDomainToUi,
            productCountRepository = productCountRepository
        )
        coEvery { getAllCategoriesUseCase() } returns testDomainCategories
        coEvery { getAllProductsUseCase() } returns testDomainProducts
        coEvery { getAllTagsUseCase() } returns testDomainTags
        every { mapProductFromDomainToUi.map(any()) } returns testProduct
        every { mapCategoriesFromDomainToUi.map(any()) } returns testCategories
        every { mapTagsFromDomainToUi.map(any()) } returns testTags
//        every { viewModel.uiState } returns MutableStateFlow(
//            CatalogUiState.Loaded(
//                categories = mapCategoriesFromDomainToUi.map(testDomainCategories)
//                    .toImmutableList(),
//                nowProducts = listOf(testProduct),
//                countsMap = mapOf(),
//                tags = mapTagsFromDomainToUi.map(testDomainTags).toImmutableList(),
//                totalPrice = TEST_TOTAL_PRICE,
//                filterTags = testFilterTags.values.toList()
//            )
//        ).asStateFlow()
    }

    @Test
    fun `should update products for tags`() {
        every { productCountRepository.allProducts } returns mapOf(1 to testProduct)

        viewModel.onUiEvent(CatalogUiEvents.UpdateProductsForTags)

        val uiState = viewModel.uiState.value as? CatalogUiState.Loaded ?: return
        val expectedNowProduct = listOf(testProduct)
        assert(expectedNowProduct == uiState.nowProducts)
    }

    @Test
    fun `should update filter tags`() {
        viewModel.onUiEvent(CatalogUiEvents.UpdateFilterTags(id = 2))

        val uiState = viewModel.uiState.value as? CatalogUiState.Loaded ?: return

        val expectedFilterTags = mapOf(
            2 to FilterTag(id = 2, title = WITHOUT_MEAT_STRING),
            4 to FilterTag(id = 4, title = ACUTE_STRING),
            6 to FilterTag(id = 6, title = DISCOUNT_STRING),
        )

        assert(expectedFilterTags == uiState.filterTags)
    }

    @Test
    fun `should update saved count and total price`() {
        val savedCountMap = mutableMapOf(TEST_PRODUCT_ID to 1)

        every { productCountRepository.savedCountMap } returns savedCountMap
        every { productCountRepository.totalPrice } returns TEST_TOTAL_PRICE

        viewModel.onUiEvent(
            CatalogUiEvents.UpdateSavedCountAndTotalPrice(TEST_PRODUCT_ID, TEST_IS_MINUS)
        )

        val uiState = viewModel.uiState.value as? CatalogUiState.Loaded ?: return

        assert(savedCountMap == uiState.countsMap)
        assert(TEST_TOTAL_PRICE == uiState.totalPrice)
    }

    @Test
    fun `should update category`() {
        every { productCountRepository.allProducts } returns mapOf(TEST_PRODUCT_ID to testProduct)

        viewModel.onUiEvent(CatalogUiEvents.UpdateCategory(TEST_CATEGORY_ID))

        val uiState = viewModel.uiState.value as? CatalogUiState.Loaded ?: return

        assert(listOf(testProduct) == uiState.nowProducts)
    }

    companion object {
        private const val TEST_PRODUCT_ID = -1
        private const val TEST_PRODUCT_NAME = "Test product name"
        private const val TEST_PRODUCT_DESCRIPTION = "Test product description"
        private const val TEST_PRODUCT_PRICE_CURRENT = 200.0
        private const val TEST_PRODUCT_PRICE_OLD = 250.0
        private const val TEST_PRODUCT_MEASURE = 250
        private const val TEST_PRODUCT_MEASURE_UNIT = "г"
        private const val TEST_PRODUCT_ENERGY = 50.0
        private const val TEST_CATEGORY_ID = -11
        private const val TEST_CATEGORY_NAME = "Test category name"
        private const val TEST_TAG_ID = -111
        private const val TEST_TAG_NAME = "Test tag name"
        private const val TEST_TOTAL_PRICE = 100.0
        private const val TEST_IS_MINUS = false
    }
}