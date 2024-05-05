package com.example.foodtestapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.models.ChangeCountModel
import com.example.foodtestapp.ui.models.ProductItemModel
import com.example.foodtestapp.ui.screen.catalog.components.CatalogChangeCount
import com.example.foodtestapp.ui.screen.catalog.components.CatalogPrice
import com.example.foodtestapp.ui.screen.catalog.model.CatalogPriceModel
import com.example.foodtestapp.ui.theme.GrayBg
import com.example.foodtestapp.ui.theme.dp12
import com.example.foodtestapp.ui.theme.dp168
import com.example.foodtestapp.ui.theme.dp170
import com.example.foodtestapp.ui.theme.dp24
import com.example.foodtestapp.ui.theme.dp290
import com.example.foodtestapp.ui.theme.dp8

@SuppressLint("ModifierParameter")
@Composable
fun ProductsItem(
    productItemModel: ProductItemModel,
    navigateToDetails: (Int) -> Unit,
    updateProduct: (Int, Boolean) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    val product = productItemModel.product
    Column(
        modifier = modifier
            .width(dp168)
            .height(dp290)
            .background(color = GrayBg, shape = RoundedCornerShape(dp8))
            .clickableCircleRipple { navigateToDetails(product.id) },
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(dp170)
        ) {
            val filterTags = product.filterTags
            if (filterTags.isNotEmpty()) {
                Image(
                    painter = painterResource(
                        id = when (filterTags.first().id) {
                            6 -> R.drawable.discount
                            2 -> R.drawable.vegetarian
                            else -> R.drawable.acute
                        }
                    ),
                    contentDescription = null,
                    modifier = modifier
                        .size(dp24)
                        .padding(start = dp8, top = dp8)
                )
            }

            ImageProduct(modifier = modifier.fillMaxSize())
        }

        Column(
            modifier = modifier
                .fillMaxHeight()
                .padding(all = dp12)
        ) {
            TextBlackStyle(text = product.name, modifier = modifier.fillMaxWidth())

            TextGrayStyle(
                text = "${product.measure} ${product.measureUnit}",
                modifier = modifier.fillMaxWidth()
            )

            Spacer(modifier = modifier.height(dp12))

            val saveCount = productItemModel.saveCount
            if (saveCount > 0 && !productItemModel.isSearch) CatalogChangeCount(
                changeCountModel = ChangeCountModel(productId = product.id, saveCount = saveCount),
                updateProduct = updateProduct,
            ) else CatalogPrice(
                catalogPriceModel = CatalogPriceModel(
                    productId = product.id,
                    priceCurrent = product.priceCurrent,
                    priceOld = product.priceOld,
                    isSearch = productItemModel.isSearch
                ),
                updateProduct = updateProduct,
            )
        }
    }
}