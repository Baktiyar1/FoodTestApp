package com.example.foodtestapp.ui.screen.details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.domain.utils.convertPriceText
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.BottomButton
import com.example.foodtestapp.ui.components.DividerBox
import com.example.foodtestapp.ui.components.TextDetail
import com.example.foodtestapp.ui.screen.details.DetailsUiState
import com.example.foodtestapp.ui.screen.details.models.DetailsInfoItemModel
import com.example.foodtestapp.ui.theme.Black
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp24
import com.example.foodtestapp.ui.theme.dp8
import com.example.foodtestapp.ui.theme.dp96
import com.example.foodtestapp.ui.theme.fontStyle
import com.example.foodtestapp.ui.theme.sp34

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsLoadedScreen(
    uiState: DetailsUiState.Loaded,
    onBack: () -> Unit,
    updateProduct: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val product = uiState.product
    Scaffold(
        bottomBar = {
            if (uiState.count > 0) DetailsChangePrice(
                saveCount = uiState.count, updateProduct = updateProduct
            ) else BottomButton(text = String.format(
                stringResource(R.string.txt_add_to_card), convertPriceText(product.priceCurrent)
            ), onClick = { updateProduct(false) })
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = White)
                .padding(bottom = dp96)
                .verticalScroll(state = rememberScrollState())
        ) {
            DetailsTopImages(onBack = onBack, modifier = modifier)

            Text(
                text = product.name,
                fontSize = sp34,
                color = Black,
                fontFamily = fontStyle,
                modifier = modifier.padding(start = dp16, end = dp16, top = dp24)
            )

            TextDetail(
                text = product.description, modifier = modifier
                    .padding(top = dp8)
                    .fillMaxWidth()
            )

            DividerBox(modifier = modifier.padding(top = dp24))

            DetailsInfoItem(
                detailsInfoItemModel = DetailsInfoItemModel(
                    resId = R.string.txt_weight, infoText = "${product.measure}"
                )
            )

            DetailsInfoItem(
                detailsInfoItemModel = DetailsInfoItemModel(
                    resId = R.string.txt_energy_value,
                    infoText = convertPriceText(product.energy),
                    isEnergy = true
                )
            )

            DetailsInfoItem(
                detailsInfoItemModel = DetailsInfoItemModel(
                    resId = R.string.txt_proteins, infoText = convertPriceText(product.proteins)
                )
            )

            DetailsInfoItem(
                detailsInfoItemModel = DetailsInfoItemModel(
                    resId = R.string.txt_fats, infoText = convertPriceText(product.fats)
                )
            )

            DetailsInfoItem(
                detailsInfoItemModel = DetailsInfoItemModel(
                    resId = R.string.txt_carbohydrates,
                    infoText = convertPriceText(product.carbohydrates)
                )
            )
        }
    }
}