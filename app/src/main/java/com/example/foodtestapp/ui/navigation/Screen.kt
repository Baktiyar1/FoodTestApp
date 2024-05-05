package com.example.foodtestapp.ui.navigation

private const val SPLASH_SCREEN_ROUTE = "splash_screen"
private const val CATALOG_SCREEN_ROUTE = "catalog_screen"
private const val CARD_SCREEN_ROUTE = "card_screen"
private const val DETAILS_SCREEN_ROUTE = "detail_screen"
private const val SEARCH_SCREEN_ROUTE = "search_screen"

//interface Destinations {
//    val route: String
//    val routeWithArgs: String
//}

sealed class Screen(val route: String) {
    data object Splash : Screen(SPLASH_SCREEN_ROUTE)
    data object Catalog : Screen(CATALOG_SCREEN_ROUTE)
    data object Card : Screen(CARD_SCREEN_ROUTE)
    data object Details : Screen(DETAILS_SCREEN_ROUTE)
    data object Search : Screen(SEARCH_SCREEN_ROUTE)
}