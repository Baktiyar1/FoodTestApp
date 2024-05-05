package com.example.foodtestapp.ui.models

import androidx.compose.runtime.Immutable
import com.example.domain.utils.DEFAULT_INT

@Immutable
data class ChangeCountModel(
    val productId: Int,
    val saveCount: Int = DEFAULT_INT,
)