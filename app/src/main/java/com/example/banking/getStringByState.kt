package com.example.banking

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.banking.models.CardState

@Composable
fun getStringByState(state: CardState): String {
    return when (state) {
        CardState.EXECUTED -> stringResource(id = R.string.executed)
        CardState.DECLINED -> stringResource(id = R.string.declined)
        CardState.IN_PROGRESS -> stringResource(id = R.string.in_progress_)
    }
}