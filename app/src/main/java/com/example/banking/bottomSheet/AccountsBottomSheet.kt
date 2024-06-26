package com.example.banking.bottomSheet

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.banking.R
import com.example.banking.models.Account

@ExperimentalMaterial3Api
@Composable
fun AccountsBottomSheet(
    accountsList: List<Account>,
    onDismiss: () -> Unit,
    onAccountClick: (Account) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val containerColor = colorResource(id = R.color.surface_background_color)

    ModalBottomSheet(
        onDismissRequest = onDismiss, sheetState = modalBottomSheetState,
        containerColor = containerColor,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        AccountsList(accountsList, onAccountClick)
    }
}