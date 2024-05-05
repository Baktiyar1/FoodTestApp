package com.example.foodtestapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.foodtestapp.ui.theme.dp10
import com.example.foodtestapp.ui.theme.dp44

@Composable
fun ImageStyle(@DrawableRes res: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = res),
        contentDescription = null,
        modifier
            .size(dp44)
            .padding(all = dp10)
            .clickableNoRipple { onClick() },
    )
}