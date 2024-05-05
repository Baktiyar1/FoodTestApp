package com.example.foodtestapp.ui.screen.card.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.domain.utils.DEFAULT_DOUBLE
import com.example.domain.utils.DEFAULT_INT
import com.example.domain.utils.DEFAULT_OLD_PRICE
import com.example.domain.utils.EMPTY_STRING
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.DividerBox
import com.example.foodtestapp.ui.models.ChangeCountModel
import com.example.foodtestapp.ui.screen.card.model.CardItemModel
import com.example.foodtestapp.ui.screen.card.model.CardPriceModel
import com.example.foodtestapp.ui.theme.Black
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.dp128
import com.example.foodtestapp.ui.theme.dp135
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp46
import com.example.foodtestapp.ui.theme.dp96
import com.example.foodtestapp.ui.theme.fontStyle
import com.example.foodtestapp.ui.theme.sp14

@Composable
fun CardItem(
    cardItemModel: CardItemModel,
    updateCacheProduct: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.background(color = White)) {
        Row(
            modifier = modifier
                .height(dp128)
                .padding(dp16)
        ) {
            Image(
                painter = painterResource(id = R.drawable.food),
                contentDescription = null,
                modifier = modifier.size(dp96)
            )

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = dp16)
            ) {
                Text(
                    text = cardItemModel.productName,
                    color = Black,
                    fontFamily = fontStyle,
                    fontSize = sp14,
                    modifier = modifier.alpha(alpha = 0.87f)
                )

                Spacer(modifier = modifier.weight(1f))

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(dp46),
                ) {
                    CardChangeCount(
                        changeCountModel = ChangeCountModel(
                            productId = cardItemModel.productId, saveCount = cardItemModel.saveCount
                        ), updateCacheProduct = updateCacheProduct, modifier = modifier.width(dp135)
                    )

                    CardPrice(
                        cardPriceModel = CardPriceModel(
                            priceCurrent = cardItemModel.priceCurrent,
                            priceOld = cardItemModel.priceOld
                        )
                    )
                }
            }
        }

        DividerBox()
    }
}

@Preview
@Composable
fun CardItemPreview() {
    CardItem(cardItemModel = CardItemModel(
        productId = DEFAULT_INT,
        productName = EMPTY_STRING,
        priceCurrent = DEFAULT_DOUBLE,
        priceOld = DEFAULT_OLD_PRICE
    ), updateCacheProduct = { _, _ -> })
}