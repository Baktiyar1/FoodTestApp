package com.example.foodtestapp.ui.screen.catalog.model

import androidx.compose.runtime.Stable
import com.example.foodtestapp.models.FilterTag

@Stable
data class FilterBottomSheetItemModel(val filterTag: FilterTag, val isLast: Boolean)