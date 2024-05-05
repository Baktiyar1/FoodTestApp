package com.example.foodtestapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.example.foodtestapp.ui.theme.Black
import com.example.foodtestapp.ui.theme.dp20
import com.example.foodtestapp.ui.theme.fontStyle
import com.example.foodtestapp.ui.theme.sp14

@Composable
fun TextGrayStyle(
    text: String,
    style: TextStyle = TextStyle(fontSize = sp14),
    height: Dp = dp20,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = style,
        color = Black,
        fontFamily = fontStyle,
        modifier = modifier
            .height(height)
            .alpha(0.6f)
    )
}