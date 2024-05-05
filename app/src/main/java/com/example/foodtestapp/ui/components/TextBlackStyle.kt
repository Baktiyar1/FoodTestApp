package com.example.foodtestapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.foodtestapp.ui.theme.Black
import com.example.foodtestapp.ui.theme.dp20
import com.example.foodtestapp.ui.theme.fontStyle
import com.example.foodtestapp.ui.theme.sp14

@SuppressLint("ModifierParameter")
@Composable
fun TextBlackStyle(
    text: String,
    textSize: TextUnit = sp14,
    height: Dp = dp20,
    fontFamily: FontFamily = fontStyle,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = textSize,
        color = Black,
        fontFamily = fontFamily,
        modifier = modifier
            .height(height)
            .alpha(0.87f)
    )
}