package com.example.foodtestapp.ui.screen.search.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.domain.utils.EMPTY_STRING
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.TextBlackStyle
import com.example.foodtestapp.ui.components.TextGrayStyle
import com.example.foodtestapp.ui.components.clickableNoRipple
import com.example.foodtestapp.ui.theme.Black
import com.example.foodtestapp.ui.theme.GrayBg
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp24
import com.example.foodtestapp.ui.theme.dp64

@SuppressLint("UnrememberedMutableState")
@Composable
fun SearchTopBar(
    searchText: String,
    onBack: () -> Unit,
    updateText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(dp64)
            .padding(all = dp16)
            .background(color = White),
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_left_orange),
            contentDescription = null,
            modifier = modifier
                .background(color = White)
                .size(dp24)
                .clickableNoRipple { onBack() },
        )

        TextField(
            value = searchText,
            modifier = modifier
                .fillMaxSize()
                .height(dp24)
                .padding(start = dp16)
                .background(color = Color.Transparent),
            textStyle = TextStyle(color = Black),
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedTextColor = Black,
                disabledTextColor = Black,
                unfocusedTextColor = Black,
                focusedPlaceholderColor = GrayBg,
                disabledLabelColor = GrayBg,
                unfocusedLabelColor = GrayBg
            ),
            onValueChange = {
                updateText(it)
            },
            singleLine = true,
            trailingIcon = {
                if (searchText.isNotEmpty()) Image(painter = painterResource(id = R.drawable.cancel),
                    contentDescription = null,
                    modifier = modifier.clickableNoRipple { updateText(EMPTY_STRING) })
            },
            label = {
                if (searchText.isEmpty()) TextGrayStyle(text = stringResource(id = R.string.txt_search_label))
                else TextBlackStyle(text = searchText)
            },
        )
    }
}