package com.example.foodtestapp.ui.screen.catalog

import androidx.lifecycle.viewModelScope
import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import com.example.domain.models.DomainProduct
import com.example.domain.models.DomainTag
import com.example.domain.usecases.category.GetAllCategoriesUseCase
import com.example.domain.usecases.product.GetAllProductsUseCase
import com.example.domain.usecases.tag.GetAllTagsUseCase
import com.example.domain.utils.EMPTY_STRING
import com.example.foodtestapp.base.BaseUiEvents
import com.example.foodtestapp.base.BaseViewModel
import com.example.foodtestapp.models.Category
import com.example.foodtestapp.models.Product
import com.example.foodtestapp.models.Tag
import com.example.foodtestapp.ui.product.ProductCountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getAllTagsUseCase: GetAllTagsUseCase,
    private val mapProductFromDomainToUi: Mapper<DomainProduct, Product>,
    private val mapCategoriesFromDomainToUi: Mapper<List<DomainCategory>, List<Category>>,
    private val mapTagsFromDomainToUi: Mapper<List<DomainTag>, List<Tag>>,
    override val productCountRepository: ProductCountRepository
) : BaseViewModel(productCountRepository = productCountRepository) {

    private val _uiState = MutableStateFlow<CatalogUiState>(CatalogUiState.Loading)
    val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    private var categories = listOf<Category>()
    private var nowCategory = Category.unknown
    private var tags = listOf<Tag>()
    private var filterTags = productCountRepository.filterTags.values.toList()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _uiState.tryEmit(CatalogUiState.Error(error = throwable.localizedMessage ?: EMPTY_STRING))
    }

    init {
        viewModelScope.launch(context = handler + Dispatchers.IO) {
            getAllCategories()
            getAllProducts()
            getAllTags()
            updateUiState()
        }
    }

    fun onUiEvent(uiEvents: CatalogUiEvents) {
        when (uiEvents) {
            CatalogUiEvents.UpdateProductsForTags -> updateProductsForTags()
            CatalogUiEvents.GetUpdateInfo -> updateUiState()
            is CatalogUiEvents.UpdateCategory -> updateCategory(categoryId = uiEvents.categoryId)
            is CatalogUiEvents.UpdateFilterTags -> updateFilterTags(tagId = uiEvents.id)
            is CatalogUiEvents.UpdateNowProductId -> productCountRepository.nowProduct =
                productCountRepository.getNowProduct(uiEvents.productId)

            is CatalogUiEvents.UpdateSavedCountAndTotalPrice -> updateSavedCountAndTotalPrice(
                productId = uiEvents.productId, isMinus = uiEvents.isMinus
            )
        }
    }

    private fun updateProductsForTags() {
        _uiState.update { currentState ->
            val state = _uiState.value as? CatalogUiState.Loaded ?: return@update currentState
            val isSelectedFilterTags = filterTags.filter { it.isSelected }.map { it.id }
            val categoryProducts = productCountRepository.allProducts.values.toList()
                .filter { nowCategory.id == it.categoryId }
            val newProducts = if (isSelectedFilterTags.isEmpty()) categoryProducts
            else categoryProducts.filter { product -> product.filterTags.any { it.id in isSelectedFilterTags } }
            state.copy(nowProducts = newProducts)
        }
    }

    private fun updateFilterTags(tagId: Int) {
        _uiState.update { currentState ->
            val state = _uiState.value as? CatalogUiState.Loaded ?: return@update currentState
            filterTags =
                filterTags.map { if (it.id == tagId) it.copy(isSelected = !it.isSelected) else it }
            state.copy(filterTags = filterTags)
        }
    }

    private fun updateSavedCountAndTotalPrice(productId: Int, isMinus: Boolean) {
        viewModelScope.launch(context = handler + Dispatchers.IO) {
            onEvent(BaseUiEvents.UpdateSavedCountAndTotalPrice(productId = productId, isMinus = isMinus))
            _uiState.update { currentState ->
                val state = _uiState.value as? CatalogUiState.Loaded ?: return@update currentState
                state.copy(
                    countsMap = productCountRepository.savedCountMap.toMap(),
                    totalPrice = productCountRepository.totalPrice
                )
            }
        }
    }

    private fun updateCategory(categoryId: Int) {
        nowCategory = categories.firstOrNull { it.id == categoryId } ?: Category.unknown
        _uiState.update { currentState ->
            val state = _uiState.value as? CatalogUiState.Loaded ?: return@update currentState
            val newNowProducts = productCountRepository.allProducts.values.toList()
                .filter { it.categoryId == nowCategory.id }
            state.copy(nowProducts = newNowProducts)
        }
    }

    private fun updateUiState() {
        val contentState = CatalogUiState.Loaded(
            categories = categories.toImmutableList(),
            nowProducts = productCountRepository.allProducts.map { it.value }
                .filter { it.categoryId == nowCategory.id },
            countsMap = productCountRepository.savedCountMap.toMap(),
            tags = tags.toImmutableList(),
            totalPrice = productCountRepository.totalPrice,
            filterTags = productCountRepository.filterTags.values.toImmutableList()
        )
        _uiState.tryEmit(contentState)
    }

    private suspend fun getAllCategories() {
        categories = mapCategoriesFromDomainToUi.map(getAllCategoriesUseCase())
        nowCategory = categories.first()
    }

    private suspend fun getAllProducts() {
        val allProducts = mutableMapOf<Int, Product>()
        getAllProductsUseCase().forEach { allProducts[it.id] = mapProductFromDomainToUi.map(it) }
        productCountRepository.allProducts = allProducts
    }

    private suspend fun getAllTags() {
        tags = mapTagsFromDomainToUi.map(getAllTagsUseCase())
    }
}