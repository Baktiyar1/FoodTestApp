package com.example.foodtestapp.ui.manager.navigation

import kotlinx.coroutines.flow.Flow

interface NavigatorManager {
    fun destinationFlow(): Flow<Pair<String, Boolean>>
    fun navigateTo(destination: String, isClearBackStack: Boolean = false)
}