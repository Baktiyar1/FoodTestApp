package com.example.foodtestapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import com.example.domain.utils.convertPriceText
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.sp14

@Composable
fun TextOld(priceOld: Double, height: Dp = dp16) {
    TextGrayStyle(
        text = String.format(
            stringResource(id = R.string.txt_rubles), convertPriceText(priceOld)
        ),
        height = height,
        style = TextStyle(textDecoration = TextDecoration.LineThrough, fontSize = sp14)
    )
}