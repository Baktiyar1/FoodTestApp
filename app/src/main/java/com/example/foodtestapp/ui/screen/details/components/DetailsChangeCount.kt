package com.example.foodtestapp.ui.screen.details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.ChangeCountButton
import com.example.foodtestapp.ui.components.TextBlackStyle
import com.example.foodtestapp.ui.theme.GrayBg
import com.example.foodtestapp.ui.theme.dp76
import com.example.foodtestapp.ui.theme.sp18

@Composable
fun DetailsChangePrice(
    saveCount: Int, updateProduct: (Boolean) -> Unit, modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(dp76)
    ) {
        Spacer(modifier = modifier.weight(1f))

        ChangeCountButton(
            resId = R.drawable.minus, backgroundColor = GrayBg,
            onClick = { updateProduct(true) },
        )

        Spacer(modifier = modifier.weight(1f))

        TextBlackStyle(text = "$saveCount", textSize = sp18)

        Spacer(modifier = modifier.weight(1f))

        ChangeCountButton(
            resId = R.drawable.plus, backgroundColor = GrayBg,
            onClick = { updateProduct(false) },
        )

        Spacer(modifier = modifier.weight(1f))
    }
}