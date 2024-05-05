package com.example.foodtestapp.ui.screen.card.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.ChangeCountButton
import com.example.foodtestapp.ui.components.TextBlackStyle
import com.example.foodtestapp.ui.models.ChangeCountModel
import com.example.foodtestapp.ui.theme.GrayBg
import com.example.foodtestapp.ui.theme.sp18

@Composable
fun CardChangeCount(
    changeCountModel: ChangeCountModel,
    updateCacheProduct: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
    ) {
        ChangeCountButton(
            resId = R.drawable.minus,
            backgroundColor = GrayBg,
            onClick = { updateCacheProduct(changeCountModel.productId, true) },
        )

        Spacer(modifier = modifier.weight(1f))

        TextBlackStyle(text = "${changeCountModel.saveCount}", textSize = sp18)

        Spacer(modifier = modifier.weight(1f))

        ChangeCountButton(
            resId = R.drawable.plus,
            backgroundColor = GrayBg,
            onClick = { updateCacheProduct(changeCountModel.productId, false) },
        )
    }
}