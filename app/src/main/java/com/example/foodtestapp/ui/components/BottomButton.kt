package com.example.foodtestapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.foodtestapp.ui.theme.Primary
import com.example.foodtestapp.ui.theme.White
import com.example.foodtestapp.ui.theme.dp12
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp72
import com.example.foodtestapp.ui.theme.dp8
import com.example.foodtestapp.ui.theme.fontStyle
import com.example.foodtestapp.ui.theme.sp16

@Composable
fun BottomButton(
    text: String,
    onClick: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dp72)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = dp16, vertical = dp12)
                .background(color = Primary, shape = RoundedCornerShape(dp8))
                .clickableCircleRipple { onClick() },
        ) {
            Text(text = text, color = White, fontSize = sp16, fontFamily = fontStyle)
        }
    }
}