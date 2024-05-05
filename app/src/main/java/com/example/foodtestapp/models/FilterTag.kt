package com.example.foodtestapp.models

import androidx.compose.runtime.Stable

@Stable
data class FilterTag(val id: Int, val title: String, var isSelected: Boolean = false)