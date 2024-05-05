package com.example.foodtestapp.ui.screen.catalog.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.foodtestapp.ui.components.DividerBox
import com.example.foodtestapp.ui.components.TextBlackStyle
import com.example.foodtestapp.ui.screen.catalog.model.FilterBottomSheetItemModel
import com.example.foodtestapp.ui.theme.dp24
import com.example.foodtestapp.ui.theme.sp14

@Composable
fun FilterBottomSheetItem(
    model: FilterBottomSheetItemModel, onCheckedChange: (Int) -> Unit, modifier: Modifier = Modifier
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextBlackStyle(text = model.filterTag.title, textSize = sp14, height = dp24)

            Spacer(modifier = modifier.weight(1f))

            Checkbox(
                checked = model.filterTag.isSelected,
                onCheckedChange = { onCheckedChange(model.filterTag.id) },
            )
        }

        if (!model.isLast) DividerBox()
    }
}