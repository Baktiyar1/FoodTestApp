package com.example.foodtestapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodtestapp.ui.screen.card.CardUiEvents
import com.example.foodtestapp.ui.screen.card.CardViewModel
import com.example.foodtestapp.ui.screen.card.components.CardScreen
import com.example.foodtestapp.ui.screen.catalog.CatalogUiEvents
import com.example.foodtestapp.ui.screen.catalog.CatalogViewModel
import com.example.foodtestapp.ui.screen.catalog.components.CatalogScreen
import com.example.foodtestapp.ui.screen.details.DetailsUiEvents
import com.example.foodtestapp.ui.screen.details.DetailsViewModel
import com.example.foodtestapp.ui.screen.details.components.DetailsScreen
import com.example.foodtestapp.ui.screen.search.SearchUiEvents
import com.example.foodtestapp.ui.screen.search.SearchViewModel
import com.example.foodtestapp.ui.screen.search.components.SearchScreen
import com.example.foodtestapp.ui.screen.splash.SplashScreen
import com.example.foodtestapp.ui.theme.White

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier.background(color = White)
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navigateToCatalog = {
                navController.popBackStack()
                navController.navigate(Screen.Catalog.route)
            })
        }

        composable(route = Screen.Catalog.route) {
            val viewModel: CatalogViewModel = hiltViewModel()
            CatalogScreen(catalogUiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                navigateToDetails = {
                    viewModel.onUiEvent(CatalogUiEvents.UpdateNowProductId(productId = it))
                    navController.navigate(Screen.Details.route)
                },
                navigateToCard = { navController.navigate(Screen.Card.route) },
                navigateToSearch = { navController.navigate(Screen.Search.route) },
                updateCategory = { viewModel.onUiEvent(CatalogUiEvents.UpdateCategory(it)) },
                updateProduct = { productId, isMinus ->
                    viewModel.onUiEvent(CatalogUiEvents.UpdateSavedCountAndTotalPrice(productId, isMinus))
                },
                getInfo = { viewModel.onUiEvent(CatalogUiEvents.GetUpdateInfo) },
                onChangeFilterTag = { viewModel.onUiEvent(CatalogUiEvents.UpdateFilterTags(it)) },
                updateProductsForTags = { viewModel.onUiEvent(CatalogUiEvents.UpdateProductsForTags) })
        }

        composable(route = Screen.Details.route) {
            val viewModel: DetailsViewModel = hiltViewModel()

            DetailsScreen(
                uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                onBack = { navController.popBackStack() },
                updateProduct = { viewModel.onUiEvent(DetailsUiEvents.UpdateProduct(it)) },
            )
        }

        composable(route = Screen.Card.route) {
            val cardViewModel: CardViewModel = hiltViewModel()
            CardScreen(
                uiState = cardViewModel.uiState.collectAsStateWithLifecycle().value,
                navigateToBack = { navController.popBackStack() },
                updateCacheProduct = { productId, isMinus ->
                    cardViewModel.onUiEvent(CardUiEvents.UpdateCacheProduct(productId, isMinus))
                },
            )
        }

        composable(route = Screen.Search.route) {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                uiState = searchViewModel.uiState.collectAsStateWithLifecycle().value,
                updateText = { searchViewModel.onUiEvent(SearchUiEvents.UpdateSearchText(it)) },
                onBack = { navController.popBackStack() },
                navigateToDetails = {
                    searchViewModel.onUiEvent(SearchUiEvents.UpdateNowProductId(it))
                    navController.navigate(Screen.Details.route)
                },
            )
        }
    }
}