package com.example.foodtestapp.ui.screen.catalog.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.ChangeCountButton
import com.example.foodtestapp.ui.components.TextBlackStyle
import com.example.foodtestapp.ui.models.ChangeCountModel
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.sp18

@Composable
fun CatalogChangeCount(changeCountModel: ChangeCountModel, updateProduct: (Int, Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ChangeCountButton(
            resId = R.drawable.minus, backgroundColor = White,
            onClick = { updateProduct(changeCountModel.productId, true) },
        )

        Spacer(modifier = Modifier.weight(1f))

        TextBlackStyle(text = "${changeCountModel.saveCount}", textSize = sp18)

        Spacer(modifier = Modifier.weight(1f))

        ChangeCountButton(
            resId = R.drawable.plus, backgroundColor = White,
            onClick = { updateProduct(changeCountModel.productId, false) },
        )
    }
}