package com.example.foodtestapp.ui.screen.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.ImageProduct
import com.example.foodtestapp.ui.components.clickableCircleRipple
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp20
import com.example.foodtestapp.ui.theme.dp375
import com.example.foodtestapp.ui.theme.dp4
import com.example.foodtestapp.ui.theme.dp40
import com.example.foodtestapp.ui.theme.dp40_5
import com.example.foodtestapp.ui.theme.dp76

@Composable
fun DetailsTopImages(onBack: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dp375)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(dp76)
                .padding(all = dp16)
                .shadow(elevation = dp4, shape = RoundedCornerShape(dp40_5))
                .background(color = White, shape = RoundedCornerShape(dp40))
                .clickableCircleRipple { onBack() },
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = null,
                modifier = modifier
                    .width(dp20)
                    .height(dp16),
            )
        }

        ImageProduct(modifier = modifier.fillMaxSize())
    }
}