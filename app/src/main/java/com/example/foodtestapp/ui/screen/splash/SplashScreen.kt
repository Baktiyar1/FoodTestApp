package com.example.foodtestapp.ui.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.example.domain.utils.EMPTY_STRING
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.theme.Primary
import com.example.foodtestapp.ui.theme.SplashTheme
import com.example.foodtestapp.ui.theme.dp178
import com.example.foodtestapp.ui.theme.dp96
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToCatalog: () -> Unit) {
    SplashTheme {
        var startAnimation by remember { mutableStateOf(false) }
        val alphaAnim = animateFloatAsState(
            targetValue = if (startAnimation) 1f else 0f,
            animationSpec = tween(durationMillis = 3000),
            label = EMPTY_STRING
        )
        LaunchedEffect(key1 = true) {
            startAnimation = true
            delay(4000)
            navigateToCatalog()
        }
        Splash(alpha = alphaAnim.value)
    }
}

@Composable
fun Splash(alpha: Float, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(color = Primary),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = modifier
                .width(dp178)
                .height(dp96)
                .alpha(alpha)
        )
    }
}