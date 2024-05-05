package com.example.foodtestapp.ui.screen.catalog.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.foodtestapp.ui.theme.Black
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.fontStyle

@Composable
fun EmptyProducts(@StringRes resId: Int, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        Text(text = stringResource(id = resId), fontFamily = fontStyle, color = Black)
    }
}