package com.example.foodtestapp.ui.screen.catalog.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.foodtestapp.R
import com.example.foodtestapp.ui.components.BottomButton
import com.example.foodtestapp.ui.components.TextBlackStyle
import com.example.foodtestapp.ui.screen.catalog.model.FilterBottomSheetItemModel
import com.example.foodtestapp.ui.screen.catalog.model.FilterBottomSheetModel
import com.example.foodtestapp.ui.theme.dp16
import com.example.foodtestapp.ui.theme.dp24
import com.example.foodtestapp.ui.theme.dp8
import com.example.foodtestapp.ui.theme.sp16

@ExperimentalMaterial3Api
@Composable
fun FilterBottomSheet(
    model: FilterBottomSheetModel,
    setShowBottomSheet: () -> Unit,
    onCheckedChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = false, confirmValueChange = { false })
    ModalBottomSheet(onDismissRequest = { setShowBottomSheet() }, sheetState = sheetState) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dp24, vertical = dp8)
        ) {
            TextBlackStyle(
                text = stringResource(id = R.string.txt_select_dishes),
                textSize = sp16,
                height = dp24
            )

            Spacer(modifier = modifier.height(dp16))

            LazyColumn {
                items(model.filterTags) {
                    FilterBottomSheetItem(
                        model = FilterBottomSheetItemModel(filterTag = it, isLast = it.id == 6),
                        onCheckedChange = onCheckedChange
                    )
                }
            }

            BottomButton(
                text = stringResource(id = R.string.txt_done), onClick = setShowBottomSheet
            )
        }
    }
}