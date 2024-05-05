package com.example.foodtestapp.ui.screen.card.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.TextBlackStyle
import com.example.foodtestapp.ui.components.clickableNoRipple
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp24
import com.example.foodtestapp.ui.theme.dp32
import com.example.foodtestapp.ui.theme.dp56
import com.example.foodtestapp.ui.theme.sp18

@Composable
fun CardToolBar(navigateToBack: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(dp56)
            .padding(all = dp16)
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_left_orange),
            contentDescription = null,
            modifier = modifier
                .size(dp24)
                .clickableNoRipple { navigateToBack() },
        )

        TextBlackStyle(
            text = stringResource(id = R.string.txt_card),
            textSize = sp18,
            height = dp24,
            fontFamily = FontFamily(Font(R.font.inter_bold)),
            modifier = modifier.padding(start = dp32),
        )
    }
}