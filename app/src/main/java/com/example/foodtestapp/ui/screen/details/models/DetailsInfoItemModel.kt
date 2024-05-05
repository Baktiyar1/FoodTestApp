package com.example.foodtestapp.ui.screen.details.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class DetailsInfoItemModel(
    @StringRes val resId: Int,
    val infoText: String,
    val isEnergy: Boolean = false,
)