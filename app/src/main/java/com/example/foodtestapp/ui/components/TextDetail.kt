package com.example.foodtestapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.foodtestapp.ui.theme.Black
import com.example.foodtestapp.ui.theme.dp13
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.sp16

@Composable
fun TextDetail(
    text: String,
    isInfo: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier.padding(vertical = dp13)
) {
    Text(
        text = text,
        fontSize = sp16,
        color = Black,
        modifier = modifier
            .padding(horizontal = dp16)
            .alpha(alpha = if (isInfo) 0.87f else 0.6f)
    )
}