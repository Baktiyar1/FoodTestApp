package com.example.foodtestapp.ui.screen.card.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.domain.utils.DEFAULT_OLD_PRICE
import com.example.domain.utils.convertPriceText
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.TextBlackStyle
import com.example.foodtestapp.ui.components.TextOld
import com.example.foodtestapp.ui.screen.card.model.CardPriceModel
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp20
import com.example.foodtestapp.ui.theme.dp24
import com.example.foodtestapp.ui.theme.sp16

@Composable
fun CardPrice(cardPriceModel: CardPriceModel, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = dp16)
    ) {
        Spacer(modifier = modifier.weight(1f))

        TextBlackStyle(
            text = String.format(
                stringResource(id = R.string.txt_rubles),
                convertPriceText(cardPriceModel.priceCurrent)
            ), textSize = sp16, height = dp24
        )

        if (cardPriceModel.priceOld != DEFAULT_OLD_PRICE) {
            Spacer(modifier = modifier.weight(1f))
            TextOld(priceOld = cardPriceModel.priceOld, height = dp20)
        }


        Spacer(modifier = modifier.weight(1f))
    }
}