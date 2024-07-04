@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.banking.presentation.all_transactions_screen.components.select_date_bottom_sheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.banking.R
import com.example.banking.ui.theme.BankingTheme

@Composable
fun SelectDateBottomSheet(
    onDismiss: () -> Unit,
    onSubmitButtonClick: (startDate: Long, endDate: Long) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val containerColor = colorResource(id = R.color.surface_background_color)
    ModalBottomSheet(
        onDismissRequest = onDismiss, sheetState = modalBottomSheetState,
        containerColor = containerColor,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        DateRangeColumn(
            onSubmitButtonClick = onSubmitButtonClick,
            viewModel = DateRangeViewModel()
        )
    }
}


@Composable
@Preview
fun TextFieldWithLabelPreview() {
    BankingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.surface_background_color)
        ) {
            Box(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.inner_padding)),
                contentAlignment = Alignment.Center
            ) {

            }
        }
    }
}