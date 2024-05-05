package com.example.foodtestapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.foodtestapp.R

@Composable
fun ImageProduct(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.food),
        contentDescription = null,
        modifier = modifier
    )
}