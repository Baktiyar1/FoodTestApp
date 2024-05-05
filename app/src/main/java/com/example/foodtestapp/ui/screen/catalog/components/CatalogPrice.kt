package com.example.foodtestapp.ui.screen.catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.domain.utils.DEFAULT_OLD_PRICE
import com.example.domain.utils.convertPriceText
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.TextBlackStyle
import com.example.foodtestapp.ui.components.TextOld
import com.example.foodtestapp.ui.components.clickableCircleRipple
import com.example.foodtestapp.ui.screen.catalog.model.CatalogPriceModel
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.dp40
import com.example.foodtestapp.ui.theme.dp8
import com.example.foodtestapp.ui.theme.sp16

@Composable
fun CatalogPrice(
    catalogPriceModel: CatalogPriceModel,
    updateProduct: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val rowModifier = modifier
        .background(color = White, shape = RoundedCornerShape(dp8))
        .height(dp40)
    Row(
        modifier = if (catalogPriceModel.isSearch) rowModifier
        else rowModifier.clickableCircleRipple {
            updateProduct(catalogPriceModel.productId, false)
        }, verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(2f))

        TextBlackStyle(
            text = String.format(
                stringResource(id = R.string.txt_rubles),
                convertPriceText(catalogPriceModel.priceCurrent)
            ), textSize = sp16
        )

        if (catalogPriceModel.priceOld != DEFAULT_OLD_PRICE) {
            Spacer(modifier = Modifier.weight(1f))
            TextOld(priceOld = catalogPriceModel.priceOld)
        }

        Spacer(modifier = Modifier.weight(2f))
    }
}