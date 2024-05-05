package com.example.foodtestapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.foodtestapp.ui.theme.Black
import com.example.foodtestapp.ui.theme.dp1

@Composable
fun DividerBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dp1)
            .alpha(alpha = 0.12f)
            .background(color = Black)
    )
}