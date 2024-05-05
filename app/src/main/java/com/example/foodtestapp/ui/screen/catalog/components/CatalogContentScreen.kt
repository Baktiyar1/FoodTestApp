package com.example.foodtestapp.ui.screen.catalog.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.domain.utils.DEFAULT_DOUBLE
import com.example.domain.utils.DEFAULT_FLOAT
import com.example.domain.utils.DEFAULT_INT
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.ProductsItem
import com.example.foodtestapp.ui.models.ProductItemModel
import com.example.foodtestapp.ui.screen.catalog.model.CatalogContentScreenModel
import com.example.foodtestapp.ui.theme.Black
import com.example.foodtestapp.ui.theme.Primary
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.dp1
import com.example.foodtestapp.ui.theme.dp12
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp40
import com.example.foodtestapp.ui.theme.dp56
import com.example.foodtestapp.ui.theme.dp72
import com.example.foodtestapp.ui.theme.dp8
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogContentScreen(
    model: CatalogContentScreenModel,
    updateCategory: (Int) -> Unit,
    navigateToDetails: (Int) -> Unit,
    updateProduct: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val categories = model.categories
    val pagerState = rememberPagerState(DEFAULT_INT, DEFAULT_FLOAT) { categories.size }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(
                top = dp56, bottom = if (model.totalPrice != DEFAULT_DOUBLE) dp72 else dp1
            )
    ) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = White,
            modifier = modifier,
            indicator = {},
            divider = {},
        ) {
            categories.forEachIndexed { index, category ->
                val selected = pagerState.currentPage == index
                Tab(
                    modifier = modifier,
                    selected = selected,
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                        updateCategory(category.id)
                    },
                    text = {
                        Text(
                            text = category.name,
                            color = if (selected) White else Black,
                            modifier = if (selected) modifier
                                .background(
                                    color = Primary, shape = RoundedCornerShape(dp8)
                                )
                                .padding(horizontal = dp16, vertical = dp12)
                            else modifier.padding(horizontal = dp16, vertical = dp12)
                        )
                    },
                )
            }
        }

        HorizontalPager(
            modifier = modifier,
            state = pagerState,
            userScrollEnabled = false,
            pageContent = {
                if (model.nowProducts.isNotEmpty()) LazyVerticalGrid(
                    verticalArrangement = Arrangement.spacedBy(dp16),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(dp12),
                    contentPadding = PaddingValues(bottom = dp40),
                    userScrollEnabled = true,
                    modifier = Modifier
                        .padding(all = dp16)
                        .nestedScroll(
                            connection = remember {
                                object : NestedScrollConnection {
                                    override fun onPreScroll(
                                        available: Offset, source: NestedScrollSource
                                    ): Offset = if (available.y >= 0) Offset.Zero else Offset(
                                        x = 0f, y = scrollState.dispatchRawDelta(available.y)
                                    )
                                }
                            },
                        )
                ) {
                    items(items = model.nowProducts) { product ->
                        ProductsItem(
                            modifier = Modifier.animateItemPlacement(),
                            productItemModel = ProductItemModel(
                                product = product,
                                saveCount = model.countsMap[product.id] ?: DEFAULT_INT,
                            ),
                            navigateToDetails = navigateToDetails,
                            updateProduct = updateProduct,
                        )
                    }
                } else EmptyProducts(resId = R.string.txt_products_are_missing)
            },
        )
    }
}