package com.example.foodtestapp.ui.manager.navigation

import com.example.domain.utils.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

class NavigatorManagerImpl @Inject constructor() : NavigatorManager {
    private val destinationFlow = MutableStateFlow(EMPTY_STRING to false)

    override fun destinationFlow(): Flow<Pair<String, Boolean>> {
        return destinationFlow.asStateFlow().filterNotNull()
    }

    override fun navigateTo(destination: String, isClearBackStack: Boolean) {
        destinationFlow.tryEmit(Pair(destination, isClearBackStack))
    }
}