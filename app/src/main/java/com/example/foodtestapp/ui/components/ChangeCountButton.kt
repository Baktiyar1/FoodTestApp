package com.example.foodtestapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.foodtestapp.ui.theme.dp44
import com.example.foodtestapp.ui.theme.dp8

@SuppressLint("ModifierParameter")
@Composable
fun ChangeCountButton(
    resId: Int, backgroundColor: Color, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(dp44)
            .background(color = backgroundColor, shape = RoundedCornerShape(dp8))
            .clickableCircleRipple { onClick() },
    ) {
        Image(painter = painterResource(id = resId), contentDescription = null)
    }
}