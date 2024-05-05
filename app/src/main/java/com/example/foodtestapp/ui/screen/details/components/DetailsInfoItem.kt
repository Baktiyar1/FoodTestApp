package com.example.foodtestapp.ui.screen.details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.DividerBox
import com.example.foodtestapp.ui.components.TextDetail
import com.example.foodtestapp.ui.screen.details.models.DetailsInfoItemModel

@SuppressLint("ModifierParameter")
@Composable
fun DetailsInfoItem(detailsInfoItemModel: DetailsInfoItemModel, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        TextDetail(text = stringResource(detailsInfoItemModel.resId))

        Spacer(modifier = modifier.weight(1f))

        TextDetail(
            text = String.format(
                stringResource(if (detailsInfoItemModel.isEnergy) R.string.txt_kilocalories else R.string.txt_grams),
                detailsInfoItemModel.infoText
            ), isInfo = true
        )
    }

    DividerBox(modifier = modifier)
}

@Preview
@Composable
fun DetailsInfoItemPreview() {
    DetailsInfoItem(
        detailsInfoItemModel = DetailsInfoItemModel(
            resId = R.string.txt_fats,
            infoText = "",
        ), modifier = Modifier.background(MaterialTheme.colorScheme.background)
    )
}