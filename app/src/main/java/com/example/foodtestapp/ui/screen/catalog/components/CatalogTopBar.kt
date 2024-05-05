package com.example.foodtestapp.ui.screen.catalog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.ImageStyle
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp8

@Composable
fun CatalogTopBar(
    navigateToSearch: () -> Unit, setShowBottomSheet: () -> Unit, modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = dp8, end = dp8, top = dp16)
    ) {
        ImageStyle(res = R.drawable.menu, onClick = setShowBottomSheet)

        Spacer(modifier = modifier.weight(1f))

        Image(painter = painterResource(id = R.drawable.logo_orange), contentDescription = null)

        Spacer(modifier = modifier.weight(1f))

        ImageStyle(res = R.drawable.search, onClick = navigateToSearch)
    }
}